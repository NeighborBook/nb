package com.nb.module.nb.customer.api.book.exception;


import com.zjk.module.common.base.exception.IBusinessCode;

public enum BookCode implements IBusinessCode {

	/******************** isbn ********************/
	B0001("B0001", "地址不能为空。userCode:{0}", "地址不能为空");

	private String clazz;

	private String code;

	private String message;

	private String customerMessage;

	BookCode(String code, String message, String customerMessage) {
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
