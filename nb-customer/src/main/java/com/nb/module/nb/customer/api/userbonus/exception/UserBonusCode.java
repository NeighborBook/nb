package com.nb.module.nb.customer.api.userbonus.exception;


import com.zjk.module.common.base.exception.IBusinessCode;

public enum UserBonusCode implements IBusinessCode {

	/******************** isbn ********************/
	UB0001("UB0001", "积分已更新，请刷新页面。userCode:{0}", "积分已更新，请刷新页面"),
	UB0002("UB0002", "用户积分不存在，请初始化。userCode:{0}", "用户积分不存在，请初始化"),
	UB0003("UB0003", "积分不足。userCode:{0}, currentBonus:{1}, realBonus(2)", "积分不足");

	private String clazz;

	private String code;

	private String message;

	private String customerMessage;

	UserBonusCode(String code, String message, String customerMessage) {
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
