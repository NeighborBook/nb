package com.nb.module.nb.customer.api.usercheckin.exception;


import com.zjk.module.common.base.exception.IBusinessCode;

public enum UserCheckInCode implements IBusinessCode {

	/******************** isbn ********************/
	UCI0001("UCI0001", "您已经签过到啦，请明天再来。userCode:{0}, checkIn:{1}", "您已经签过到啦，请明天再来");

	private String clazz;

	private String code;

	private String message;

	private String customerMessage;

	UserCheckInCode(String code, String message, String customerMessage) {
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
