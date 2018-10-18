package com.nb.module.nb.customer.api.isbn.convert.exception;


import com.zjk.module.common.base.exception.IBusinessCode;

public enum BookConvertCode implements IBusinessCode {

	/******************** isbn ********************/
	BC0001("BC0001", "图书没有找到。isbn:{0}", "图书没有找到");

	private String clazz;

	private String code;

	private String message;

	private String customerMessage;

	BookConvertCode(String code, String message, String customerMessage) {
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
