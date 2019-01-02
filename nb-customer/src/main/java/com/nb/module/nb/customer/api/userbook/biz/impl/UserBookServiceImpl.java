package com.nb.module.nb.customer.api.userbook.biz.impl;

import com.nb.module.nb.customer.api.book.biz.IBookService;
import com.nb.module.nb.customer.api.userbonus.biz.IUserBonusService;
import com.nb.module.nb.customer.api.userbonus.constant.UserBonusConstant;
import com.nb.module.nb.customer.api.userbonus.domain.UserBonus;
import com.nb.module.nb.customer.api.userbonus.domain.UserBonusTemplate;
import com.nb.module.nb.customer.api.userbook.biz.IUserBookService;
import com.nb.module.nb.customer.api.userbook.constant.UserBookConstant;
import com.nb.module.nb.customer.api.userbook.domain.UserBook;
import com.nb.module.nb.customer.api.userbook.domain.UserBookCount;
import com.nb.module.nb.customer.api.userbook.domain.UserBookMinInfo;
import com.nb.module.nb.customer.api.userbook.domain.UserBookUserInfo;
import com.nb.module.nb.customer.api.weixin.user.biz.IWeixinUserService;
import com.nb.module.nb.customer.base.userbook.biz.ITNBUserBookService;
import com.nb.module.nb.customer.base.userbook.domain.TNBUserBook;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserBookServiceImpl extends CommonServiceImpl implements IUserBookService {

	@Autowired
	private ITNBUserBookService userBookService;
	@Autowired
	private IBookService bookService;
	@Autowired
	private IWeixinUserService weixinUserService;

	@Autowired
	private IUserBonusService userBonusService;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public UserBook findOneByUserCodeAndBookCode(String userCode, String bookCode) {
		return mapOneIfNotNull(userBookService.findOneByUserCodeAndBookCode(userCode, bookCode), e -> convert(e));
	}

	private UserBook convert(TNBUserBook e) {
		UserBook userBook = new UserBook(e.getUserCode(), e.getBookCode(), e.getBookCount(), e.getSharable(), e.getLentAmount());
		return userBook;
	}

	@Override
	@Transactional
	public UserBonus save(UserBook vo) {
		UserBonus userBonus = null;
		// 没有sharable则默认 1-可共享的
		if (null == vo.getSharable()) {
			vo.setSharable(UserBookConstant.SHARABLE);
		}
		// 查询db
		TNBUserBook po = userBookService.findOneByUserCodeAndBookCode(vo.getUserCode(), vo.getBookCode());
		if (null == po) {
			po = new TNBUserBook();
			po.setUserCode(vo.getUserCode());
			po.setBookCode(vo.getBookCode());
			// 如果是第一次绑定，并且shareable = 1-可共享的，则送积分
			if (UserBookConstant.SHARABLE == vo.getSharable()) {
				userBonus = userBonusService.operate(new UserBonusTemplate(vo.getBaseUserBonus(), UserBonusConstant.USER_BONUS_ADD_BOOK));
			}
		}
		po.setBookCount(vo.getBookCount());
		po.setSharable(vo.getSharable());
		// lentAmount默认0
		if (null == vo.getLentAmount()) {
			vo.setLentAmount(0);
		}
		po.setLentAmount(vo.getLentAmount());
		userBookService.save(po);

		return userBonus;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<UserBook> findAllByBookCodeAndSharable(String bookCode, Integer sharable, Pageable pageable) {
		return userBookService.findAllByBookCodeAndSharable(bookCode, sharable, pageable).map(e -> convert(e));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<UserBookMinInfo> findAllByTagCodeAndUserCode(List<String> tagCodes, Integer sharable, String userCode, Pageable pageable) {
		return bookService.findAllByTagCodeAndUserCode(tagCodes, sharable, userCode, pageable)
				.map(e -> mapOneIfNotNull(userBookService.findOneByUserCodeAndBookCode(userCode, e.getCode()), s -> new UserBookMinInfo(s.getUserCode(), e, s.getBookCount(), s.getSharable(), s.getLentAmount())));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<UserBookMinInfo> findAllBySearchAndUserCode(String search, String userCode, Pageable pageable) {
		return bookService.findAllBySearchAndUserCode(search, userCode, pageable)
				.map(e -> mapOneIfNotNull(userBookService.findOneByUserCodeAndBookCode(userCode, e.getCode()), s -> new UserBookMinInfo(s.getUserCode(), e, s.getBookCount(), s.getSharable(), s.getLentAmount())));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<UserBookUserInfo> findAllUserInfoByBookCodeAndSharable(String bookCode, Integer sharable, Pageable pageable) {
		return findAllByBookCodeAndSharable(bookCode, sharable, pageable)
				.map(e -> new UserBookUserInfo(weixinUserService.findOneByCode(e.getUserCode()), e.getBookCode(), e.getBookCount(), e.getSharable(), e.getLentAmount(), weixinUserService.findUserLocationByCode(e.getUserCode())));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<UserBookCount> findAllByLbsIdInAndUserCodeNot(List<String> lbsId, String userCode, Pageable pageable) {
		return userBookService.findAllByLbsIdInAndUserCodeNot(lbsId, userCode, pageable).map(e -> new UserBookCount(e.getUserCode(), e.getBookCount()));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Long countByUserCode(String userCode) {
		return userBookService.countByUserCode(userCode);
	}
}
