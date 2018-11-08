package com.nb.module.nb.customer.api.orderform.exception;


import com.zjk.module.common.base.exception.IBusinessCode;

public enum OrderFormCode implements IBusinessCode {

	/******************** isbn ********************/
	OF0001("OF0001", "同一本书只能发起一次借书请求。fromUserCode:{0}, bookCode:{1}, toUserCode:{2}", "您已经借过这本书啦，请耐心等待对方反馈");

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
