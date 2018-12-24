package com.nb.module.partner.weixin.client.exception;


import com.zjk.module.common.base.exception.IBusinessCode;

public enum WeixinCode implements IBusinessCode {

	/******************** isbn ********************/
	WX0001("WX0001", "生成ACCESS_TOKEN失败", "生成ACCESS_TOKEN失败"),
	WX0002("WX0002", "生成JSAPI_TICKET失败", "生成JSAPI_TICKET失败"),
	WX0003("WX0003", "生成授权ACCESS_TOKEN失败", "生成授权ACCESS_TOKEN失败"),
	WX0004("WX0004", "获取用户信息失败", "获取用户信息失败"),
	WX0005("WX0005", "发送模板消息失败", "发送模板消息失败"),
	WX0006("WX0006", "获取用户是否关注失败", "获取用户是否关注失败");

	private String clazz;

	private String code;

	private String message;

	private String customerMessage;

	WeixinCode(String code, String message, String customerMessage) {
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
