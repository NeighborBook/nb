package com.nb.module.nb.customer.api.weixin.exception;


import com.zjk.module.common.base.exception.IBusinessCode;

public enum WeixinLoginCode implements IBusinessCode {

	/******************** isbn ********************/
	WXL0001("WXL0001", "登陆类型不正确:{0}", "登陆类型不正确");

	private String clazz;

	private String code;

	private String message;

	private String customerMessage;

	WeixinLoginCode(String code, String message, String customerMessage) {
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
