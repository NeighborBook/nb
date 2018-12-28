package com.nb.module.nb.customer.api.userfollow.exception;


import com.zjk.module.common.base.exception.IBusinessCode;

public enum UserFollowCode implements IBusinessCode {

	/******************** isbn ********************/
	UF0001("UF0001", "您已经关注该用户。userCode:{0}, userFollowCode:{1}", "您已经关注该用户"),
	UF0002("UF0002", "您尚未关注该用户。userCode:{0}, userFollowCode:{1}", "您尚未关注该用户");

	private String clazz;

	private String code;

	private String message;

	private String customerMessage;

	UserFollowCode(String code, String message, String customerMessage) {
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
