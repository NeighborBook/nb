package com.nb.module.nb.customer.api.weixin.exception;


import com.zjk.module.common.base.exception.IBusinessCode;

public enum WeixinCode implements IBusinessCode {

	/******************** isbn ********************/
	WX0001("WX0001", "地址已经绑定。userCode:{0}, lbsId:{1}, tagCode:{2}", "地址已经绑定");

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
