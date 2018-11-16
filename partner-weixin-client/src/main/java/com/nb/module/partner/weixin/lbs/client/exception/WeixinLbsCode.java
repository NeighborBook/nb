package com.nb.module.partner.weixin.lbs.client.exception;


import com.zjk.module.common.base.exception.IBusinessCode;

public enum WeixinLbsCode implements IBusinessCode {

	/******************** isbn ********************/
	LBS0001("LBS0001", "关键字搜索失败", "关键字搜索失败");

	private String clazz;

	private String code;

	private String message;

	private String customerMessage;

	WeixinLbsCode(String code, String message, String customerMessage) {
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
