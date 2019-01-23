package com.nb.module.nb.customer.api.userevent.exception;


import com.zjk.module.common.base.exception.IBusinessCode;

public enum UserEventCode implements IBusinessCode {

	/******************** isbn ********************/
	UE0001("UE0001", "活动名额已满。userCode:{0}, eventCode:{1}", "活动名额已满"),
	UE0002("UE0002", "您已经报过名了。userCode:{0}, eventCode:{1}", "您已经报过名了"),
	UE0003("UE0003", "您尚未报名。userCode:{0}, eventCode:{1}", "您尚未报名");

	private String clazz;

	private String code;

	private String message;

	private String customerMessage;

	UserEventCode(String code, String message, String customerMessage) {
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
