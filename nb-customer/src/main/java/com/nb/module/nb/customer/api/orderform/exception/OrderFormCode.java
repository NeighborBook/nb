package com.nb.module.nb.customer.api.orderform.exception;


import com.zjk.module.common.base.exception.IBusinessCode;

public enum OrderFormCode implements IBusinessCode {

	/******************** isbn ********************/
	OF0001("OF0001", "同一本书只能发起一次借书请求。ownerUserCode:{0}, bookCode:{1}, borrowerUserCode:{2}", "您已经借过这本书啦，请耐心等待对方反馈"),
	OF0002("OF0002", "没有这本书。ownerUserCode:{0}, bookCode:{1}", "没有这本书"),
	OF0003("OF0003", "这本书没有库存拉。ownerUserCode:{0}, bookCode:{1}, bookCount:{2}, lentAmount:{3}", "这本书没有库存拉"),
	OF0004("OF0004", "订单不存在。orderCode:{0}", "订单不存在"),
	OF0005("OF0005", "订单明细类型不存在。orderCode:{0}, orderDetailType:{1}", "订单明细类型不存在"),
	OF0006("OF0006", "订单明细状态不存在。orderCode:{0}, orderDetailStatus:{1}", "订单明细状态不存在"),
	OF0007("OF0007", "找不到信息发送人。orderCode:{0}", "找不到信息发送人"),
	OF0008("OF0008", "订单已经取消。orderCode:{0}", "订单已经取消"),
	OF0009("OF0009", "订单已经结束。orderCode:{0}", "订单已经结束"),
	OF0010("OF0010", "订单内容不存在。orderCode:{0}", "订单内容不存在");

	private String clazz;

	private String code;

	private String message;

	private String customerMessage;

	OrderFormCode(String code, String message, String customerMessage) {
		this.clazz = this.getClass().getName();
		this.code = code;
		this.message = message;
		this.customerMessage = customerMessage;
	}

	@Override
	public String getClazz() {
		return clazz;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String getCustomerMessage() {
		return customerMessage;
	}

	@Override
	public Object getData() {
		return null;
	}
}
