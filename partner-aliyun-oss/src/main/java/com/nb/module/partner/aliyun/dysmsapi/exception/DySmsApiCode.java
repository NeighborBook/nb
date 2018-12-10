package com.nb.module.partner.aliyun.dysmsapi.exception;


import com.zjk.module.common.base.exception.IBusinessCode;

public enum DySmsApiCode implements IBusinessCode {

	/******************** isbn ********************/
	DS0001("DS0001", "短信客户端异常", "短信发送失败，请稍后再试"),
	DS0002("DS0002", "短信发送异常", "短信发送失败，请稍后再试");

	private String clazz;

	private String code;

	private String message;

	private String customerMessage;

	DySmsApiCode(String code, String message, String customerMessage) {
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
