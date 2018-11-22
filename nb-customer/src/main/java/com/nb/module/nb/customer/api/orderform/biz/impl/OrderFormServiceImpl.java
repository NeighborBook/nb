package com.nb.module.nb.customer.api.orderform.biz.impl;

import com.nb.module.nb.customer.api.book.biz.IBookService;
import com.nb.module.nb.customer.api.book.domain.BookMinInfo;
import com.nb.module.nb.customer.api.orderform.biz.IOrderFormService;
import com.nb.module.nb.customer.api.orderform.constant.OrderDetailStatusConstant;
import com.nb.module.nb.customer.api.orderform.constant.OrderDetailTypeBorrowConstant;
import com.nb.module.nb.customer.api.orderform.constant.OrderFormConstant;
import com.nb.module.nb.customer.api.orderform.domain.*;
import com.nb.module.nb.customer.api.orderform.exception.OrderFormCode;
import com.nb.module.nb.customer.api.userbook.biz.IUserBookService;
import com.nb.module.nb.customer.api.userbook.domain.UserBook;
import com.nb.module.nb.customer.api.weixin.message.biz.IMessageService;
import com.nb.module.nb.customer.api.weixin.user.biz.IWeixinUserService;
import com.nb.module.nb.customer.base.orderborrow.biz.ITNBOrderBorrowService;
import com.nb.module.nb.customer.base.orderborrow.domain.TNBOrderBorrow;
import com.nb.module.nb.customer.base.orderform.biz.ITNBOrderFormService;
import com.nb.module.nb.customer.base.orderform.domain.TNBOrderForm;
import com.nb.module.nb.customer.base.orderformdetail.biz.ITNBOrderFormDetailService;
import com.nb.module.nb.customer.base.orderformdetail.domain.TNBOrderFormDetail;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
public class OrderFormServiceImpl extends CommonServiceImpl implements IOrderFormService {

	@Autowired
	private ITNBOrderFormService orderFormService;
	@Autowired
	private ITNBOrderFormDetailService orderFormDetailService;
	@Autowired
	private ITNBOrderBorrowService orderBorrowService;

	@Autowired
	private IMessageService weixinMessageService;
	@Autowired
	private IWeixinUserService weixinUserService;
	@Autowired
	private IBookService bookService;
	@Autowired
	private IUserBookService userBookService;

	/**************************************************************************************************************************************************************/

//	private Date addWeeks(Date date, Integer amount) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		calendar.add(Calendar.WEEK_OF_YEAR, amount);
//		return calendar.getTime();
//	}
	private Date addDays(Date date, Integer amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, amount);
		return calendar.getTime();
	}

	/**************************************************************************************************************************************************************/

	private <T> OrderForm<T> checkOrderForm(String orderCode, Date updated, Function<String, OrderForm<T>> function) {
		OrderForm<T> orderForm = checkIfNullThrowException(function.apply(orderCode), new BusinessException(OrderFormCode.OF0004, new Object[]{orderCode}));
		// 订单已经更新，请刷新页面
		if (!(orderForm.getUpdated().getTime() == updated.getTime())) {
			throw new BusinessException(OrderFormCode.OF0011, new Object[]{orderCode});
		}
		switch (orderForm.getOrderStatus()) {
			// 订单取消
			case OrderFormConstant.ORDER_STATUS_CANCEL:
				throw new BusinessException(OrderFormCode.OF0008, new Object[]{orderCode});
				// 订单结束
			case OrderFormConstant.ORDER_STATUS_END:
				throw new BusinessException(OrderFormCode.OF0009, new Object[]{orderCode});
		}
		// 订单内容不存在
		if (null == orderForm.getOrder()) {
			throw new BusinessException(OrderFormCode.OF0010, new Object[]{orderCode});
		}
		return orderForm;
	}

	private UserBook checkUserBook(String userCode, String bookCode) {
		UserBook userBook = userBookService.findOneByUserCodeAndBookCode(userCode, bookCode);
		// 没有这本书
		if (null == userBook) {
			throw new BusinessException(OrderFormCode.OF0002, new Object[]{userCode, bookCode});
		}
		return userBook;
	}

	private void checkOrderBorrow(BorrowApply borrowApply) {
		UserBook userBook = checkUserBook(borrowApply.getOwnerUserCode(), borrowApply.getBookCode());
		// 这本书没有库存拉
		if (userBook.getBookCount() <= userBook.getLentAmount()) {
			throw new BusinessException(OrderFormCode.OF0003, new Object[]{borrowApply.getOwnerUserCode(), borrowApply.getBookCode(), userBook.getBookCount(), userBook.getLentAmount()});
		}
		List<OrderForm<OrderBorrow>> list = findAllByOwnerUserCodeAndBookCodeAndBorrowerUserCodeAndOrderStatus(borrowApply.getOwnerUserCode(), borrowApply.getBookCode(), borrowApply.getBorrowerUserCode(), OrderFormConstant.ORDER_STATUS_START);
		// 同一本书只能发起一次借书请求
		if (null != list && !list.isEmpty()) {
			throw new BusinessException(OrderFormCode.OF0001, new Object[]{borrowApply.getOwnerUserCode(), borrowApply.getBookCode(), borrowApply.getBorrowerUserCode()});
		}
	}

	/**************************************************************************************************************************************************************/

	private OrderForm<OrderBorrow> convert(TNBOrderBorrow e) {
		TNBOrderForm orderFormPO = orderFormService.findOneByCode(e.getOrderCode());
		OrderForm<OrderBorrow> orderForm = null;
		if (null != orderFormPO) {
			orderForm = new OrderForm<>(orderFormPO.getCreated(), orderFormPO.getUpdated(), orderFormPO.getCode(), orderFormPO.getOrderType(), orderFormPO.getOrderStatus());
			orderForm.setDetails(map(orderFormDetailService.findAllByCode(e.getOrderCode()), s -> new OrderFormDetail(s.getCreated(), s.getOrderDetailType(), s.getOrderDetailStatus(), s.getRemark())));
			orderForm.setOrder(mapOneIfNotNull(e, s -> new OrderBorrow(s.getOwnerUserCode(), weixinUserService.findNicknameByCode(s.getOwnerUserCode()), s.getBookCode(), s.getBorrowerUserCode(), weixinUserService.findNicknameByCode(s.getBorrowerUserCode()), s.getBookCount(), s.getStartBorrowDate(), s.getInitialReturnDate(), s.getExpectedReturnDate(), s.getActualReturnDate())));
		}
		return orderForm;
	}

	private OrderBorrow convert(BorrowApply borrowApply) {
//		Date initialReturnDate = addWeeks(borrowApply.getStartBorrowDate(), 1);
//		return new OrderBorrow(borrowApply.getOwnerUserCode(), weixinUserService.findNicknameByCode(borrowApply.getOwnerUserCode()), borrowApply.getBookCode(), borrowApply.getBorrowerUserCode(), weixinUserService.findNicknameByCode(borrowApply.getBorrowerUserCode()), borrowApply.getBookCount(), borrowApply.getStartBorrowDate(), initialReturnDate, initialReturnDate, null);
		// 借书阶段不添加时间
		return new OrderBorrow(borrowApply.getOwnerUserCode(), weixinUserService.findNicknameByCode(borrowApply.getOwnerUserCode()), borrowApply.getBookCode(), borrowApply.getBorrowerUserCode(), weixinUserService.findNicknameByCode(borrowApply.getBorrowerUserCode()), borrowApply.getBookCount(), null, null, null, null);
	}

	private TNBOrderBorrow convert(OrderForm<OrderBorrow> orderForm) {
		OrderBorrow orderBorrow = orderForm.getOrder();
		TNBOrderBorrow po = new TNBOrderBorrow();
		po.setOrderCode(orderForm.getCode());
		po.setOwnerUserCode(orderBorrow.getOwnerUserCode());
		po.setBookCode(orderBorrow.getBookCode());
		po.setBorrowerUserCode(orderBorrow.getBorrowerUserCode());
		po.setBookCount(orderBorrow.getBookCount());
		po.setStartBorrowDate(orderBorrow.getStartBorrowDate());
		po.setInitialReturnDate(orderBorrow.getInitialReturnDate());
		po.setExpectedReturnDate(orderBorrow.getExpectedReturnDate());
		po.setActualReturnDate(orderBorrow.getActualReturnDate());
		return po;
	}

	/**************************************************************************************************************************************************************/

	private <T> void save(OrderForm<T> orderForm, Consumer<OrderForm<T>> consumer) {
		// 订单主体
		TNBOrderForm po = orderFormService.newInstance();
		po.setOrderType(orderForm.getOrderType());
		po.setOrderStatus(orderForm.getOrderStatus());
		orderFormService.save(po);

		orderForm.setCreated(po.getCreated());
		orderForm.setUpdated(po.getUpdated());
		orderForm.setCode(po.getCode());
		// 订单明细
		orderForm.getDetails().forEach(e -> save(po.getCode(), e));
		// 订单
		consumer.accept(orderForm);
	}

	private void save(String orderCode, OrderFormDetail orderFormDetail) {
		TNBOrderFormDetail po = new TNBOrderFormDetail();
		po.setCode(orderCode);
		po.setOrderDetailType(orderFormDetail.getOrderDetailType());
		po.setOrderDetailStatus(orderFormDetail.getOrderDetailStatus());
		po.setRemark(orderFormDetail.getRemark());
		orderFormDetailService.save(po);

		orderFormDetail.setCreated(po.getCreated());
	}

	private <T> void updateOrderForm(OrderForm<T> orderForm) {
		TNBOrderForm po = orderFormService.findOneByCode(orderForm.getCode());
		if (null != po) {
			po.setOrderStatus(orderForm.getOrderStatus());
			orderFormService.save(po);
		}
	}

	private void updateOrderBorrow(OrderForm<OrderBorrow> orderForm) {
		TNBOrderBorrow po = orderBorrowService.findOneByOrderCode(orderForm.getCode());
		if (null != po) {
			po.setExpectedReturnDate(orderForm.getOrder().getExpectedReturnDate());
			po.setActualReturnDate(orderForm.getOrder().getActualReturnDate());
			orderBorrowService.save(po);
		}
	}

	private void updateUserBook(OrderForm<OrderBorrow> orderForm, String operate) {
		UserBook userBook = checkUserBook(orderForm.getOrder().getOwnerUserCode(), orderForm.getOrder().getBookCode());
		// 锁定库存
		if (OrderFormConstant.ORDER_OPERATE_LOCK.equals(operate)) {
			userBook.setLentAmount(userBook.getLentAmount() + orderForm.getOrder().getBookCount());
		}
		// 释放库存
		else if (OrderFormConstant.ORDER_OPERATE_UNLOCK.equals(operate)) {
			userBook.setLentAmount(userBook.getLentAmount() - orderForm.getOrder().getBookCount());
		}
		userBookService.save(userBook);
	}

	/**************************************************************************************************************************************************************/

	private void sendBookLendingReminder(OrderForm<OrderBorrow> orderForm) {
		weixinMessageService.sendBookLendingReminder(weixinUserService.findOpenidByCode(orderForm.getOrder().getOwnerUserCode()),
				weixinUserService.findNicknameByCode(orderForm.getOrder().getBorrowerUserCode()),
				mapOneIfNotNull(bookService.findOneByCode(orderForm.getOrder().getBookCode()), e -> e.getTitle()),
//				orderForm.getOrder().getStartBorrowDate(),
				new Date(),
				orderForm.getCode());
	}

	private void sendBookLendingStatusReminder(OrderForm<OrderBorrow> orderForm, String userCode, String status) {
		weixinMessageService.sendBookLendingStatusReminder(weixinUserService.findOpenidByCode(userCode),
				mapOneIfNotNull(bookService.findOneByCode(orderForm.getOrder().getBookCode()), e -> e.getTitle()),
				status,
				new Date(),
				orderForm.getCode());
	}

	/**************************************************************************************************************************************************************/

	private OrderForm<OrderBorrow> processWhenFindAllOrderBorrow(OrderForm<OrderBorrow> orderForm) {
		// 订单明细取最后一个环节
		if (null != orderForm.getDetails() && !orderForm.getDetails().isEmpty()) {
			OrderFormDetail currentDetail = orderForm.getDetails().get(orderForm.getDetails().size() - 1);
			List<OrderFormDetail> details = new ArrayList<>();
			details.add(currentDetail);
			orderForm.setDetails(details);
		}
		// 不要userBooks
		BookMinInfo bookMinInfo = new BookMinInfo(bookService.findOneByCode(orderForm.getOrder().getBookCode()));
		bookMinInfo.setUserBooks(null);
		orderForm.getOrder().setBookMinInfo(bookMinInfo);
		return orderForm;
	}

	/**************************************************************************************************************************************************************/

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<OrderForm<OrderBorrow>> findAllByOwnerUserCode(String ownerUserCode, Pageable pageable) {
		return orderBorrowService.findAllByOwnerUserCode(ownerUserCode, pageable)
				.map(e -> mapOneIfNotNull(findOrderBorrowByOrderCode(e.getOrderCode()), s -> processWhenFindAllOrderBorrow(s)));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<OrderForm<OrderBorrow>> findAllByBorrowerUserCode(String borrowerUserCode, Pageable pageable) {
		return orderBorrowService.findAllByBorrowerUserCode(borrowerUserCode, pageable)
				.map(e -> mapOneIfNotNull(findOrderBorrowByOrderCode(e.getOrderCode()), s -> processWhenFindAllOrderBorrow(s)));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public OrderForm<OrderBorrow> findOrderBorrowByOrderCode(String orderCode) {
		return mapOneIfNotNull(orderBorrowService.findOneByOrderCode(orderCode), e -> convert(e));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<OrderForm<OrderBorrow>> findAllByOwnerUserCodeAndBookCodeAndBorrowerUserCodeAndOrderStatus(String ownerUserCode, String bookCode, String borrowerUserCode, Integer orderStatus) {
		return map(orderBorrowService.findAllByOwnerUserCodeAndBookCodeAndBorrowerUserCodeAndOrderStatus(ownerUserCode, bookCode, borrowerUserCode, orderStatus), e -> convert(e));
	}

	@Override
	@Transactional
	public OrderForm<OrderBorrow> borrow(BorrowApply borrowApply) {
		OrderForm<OrderBorrow> orderForm;
		// 检查订单申请
		checkOrderBorrow(borrowApply);
		// 创建订单
		orderForm = new OrderForm<>(OrderFormConstant.ORDER_TYPE_BORROW, OrderFormConstant.ORDER_STATUS_START);
		orderForm.setOrder(convert(borrowApply));
		orderForm.getDetails().add(new OrderFormDetail(OrderDetailTypeBorrowConstant.ORDER_DETAIL_TYPE_BORROW_BORROW.getKey(), OrderDetailStatusConstant.ORDER_DETAIL_STATUS_AGREE.getKey(), borrowApply.getRemark()));
		// 保存订单
		save(orderForm, e -> orderBorrowService.save(convert(e)));
		// 发送消息
		sendBookLendingReminder(orderForm);
		return orderForm;
	}

	@Override
	@Transactional
	public OrderForm<OrderBorrow> borrowFlow(OrderFlow orderFlow) {
		// 订单
		OrderForm<OrderBorrow> orderForm = checkOrderForm(orderFlow.getOrderCode(), orderFlow.getUpdated(), e -> findOrderBorrowByOrderCode(e));
		// 订单明细类型
		OrderDetailTypeBorrowConstant orderDetailTypeBorrowConstant = checkIfNullThrowException(OrderDetailTypeBorrowConstant.findOneByKey(orderFlow.getOrderDetailType()),
				new BusinessException(OrderFormCode.OF0005, new Object[]{orderFlow.getOrderCode(), orderFlow.getOrderDetailType()}));
		// 订单明细状态
		OrderDetailStatusConstant orderDetailStatusConstant = checkIfNullThrowException(OrderDetailStatusConstant.findOneByKey(orderFlow.getOrderDetailStatus()),
				new BusinessException(OrderFormCode.OF0006, new Object[]{orderFlow.getOrderCode(), orderFlow.getOrderDetailStatus()}));
		// 发送用户
		String userCode = null;
		if (OrderFormConstant.SEND_TO_OWNER.equalsIgnoreCase(orderDetailTypeBorrowConstant.getSendTo())) {
			userCode = orderForm.getOrder().getOwnerUserCode();
		} else if (OrderFormConstant.SEND_TO_BORROWER.equalsIgnoreCase(orderDetailTypeBorrowConstant.getSendTo())) {
			userCode = orderForm.getOrder().getBorrowerUserCode();
		}
		if (StringUtils.isBlank(userCode)) {
			throw new BusinessException(OrderFormCode.OF0007, new Object[]{orderFlow.getOrderCode()});
		}
		// 保存订单明细
		OrderFormDetail detail = new OrderFormDetail(orderDetailTypeBorrowConstant.getKey(), orderDetailStatusConstant.getKey(), orderFlow.getRemark());
		save(orderFlow.getOrderCode(), detail);
		orderForm.getDetails().add(detail);
		// 订单状态
		// String status = orderDetailTypeBorrowConstant.getValue() + "--" + ;
		String status = orderDetailTypeBorrowConstant.getValue();
		// 借书流程
		switch (orderDetailTypeBorrowConstant) {
			// 借阅
			case ORDER_DETAIL_TYPE_BORROW_BORROW_CONFIRM:
				// 同意
				if (OrderDetailStatusConstant.ORDER_DETAIL_STATUS_AGREE.equals(orderDetailStatusConstant)) {
					// 锁定库存
					updateUserBook(orderForm, OrderFormConstant.ORDER_OPERATE_LOCK);

					// 开始日期
					Date startBorrowDate = new Date();
					orderForm.getOrder().setStartBorrowDate(startBorrowDate);
					// 初始归还日期
					Date initialReturnDate = addDays(startBorrowDate, 10);
					orderForm.getOrder().setExpectedReturnDate(initialReturnDate);
					// 预计归还日期 = 初始归还日期
					orderForm.getOrder().setExpectedReturnDate(initialReturnDate);
					// 更新订单明细
					updateOrderBorrow(orderForm);
					// 订单状态
					status = status + orderDetailStatusConstant.getValue();
				}
				// 不同意
				else if (OrderDetailStatusConstant.ORDER_DETAIL_STATUS_DISAGREE.equals(orderDetailStatusConstant)) {
					// 订单结束
					orderForm.setOrderStatus(OrderFormConstant.ORDER_STATUS_END);
					// 订单状态
					status = status + orderDetailStatusConstant.getValue();
				}
				break;
			// 续借
			case ORDER_DETAIL_TYPE_BORROW_RENEW_CONFIRM:
				// 同意
				if (OrderDetailStatusConstant.ORDER_DETAIL_STATUS_AGREE.equals(orderDetailStatusConstant)) {
					// 更新预计归还日期
					orderForm.getOrder().setExpectedReturnDate(addDays(orderForm.getOrder().getExpectedReturnDate(), 10));
					updateOrderBorrow(orderForm);
					// 订单状态
					status = status + orderDetailStatusConstant.getValue();
				}
				// 不同意
				else if (OrderDetailStatusConstant.ORDER_DETAIL_STATUS_DISAGREE.equals(orderDetailStatusConstant)) {
					// 订单状态
					status = status + orderDetailStatusConstant.getValue();
				}
				break;
			// 归还图书
			case ORDER_DETAIL_TYPE_BORROW_RETURN:
				// 同意
				if (OrderDetailStatusConstant.ORDER_DETAIL_STATUS_AGREE.equals(orderDetailStatusConstant)) {
					// 释放库存
					updateUserBook(orderForm, OrderFormConstant.ORDER_OPERATE_UNLOCK);
					// 更新实际归还日期
					orderForm.getOrder().setActualReturnDate(new Date());
					updateOrderBorrow(orderForm);
					// 订单结束
					orderForm.setOrderStatus(OrderFormConstant.ORDER_STATUS_END);
				}
				break;
		}
		updateOrderForm(orderForm);
		// 发送消息
		sendBookLendingStatusReminder(orderForm, userCode, status);
		return orderForm;
	}

}
