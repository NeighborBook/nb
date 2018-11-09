package com.nb.module.nb.customer.api.orderform.constant;

public class OrderFormConstant {

	/****************************************************************************/
	// ORDER_TYPE

	/**
	 * 借书
	 */
	public static final Integer ORDER_TYPE_BORROW = 1;

	/****************************************************************************/
	// ORDER_STATUS

	/**
	 * 订单取消
	 */
	public static final Integer ORDER_STATUS_CANCEL = 0;

	/**
	 * 订单开始
	 */
	public static final Integer ORDER_STATUS_START = 1;

	/**
	 * 订单结束
	 */
	public static final Integer ORDER_STATUS_END = 2;

	/****************************************************************************/
	// ORDER_DETAIL_TYPE

	/**
	 * 发起借书申请
	 */
	public static final Integer ORDER_DETAIL_TYPE_START_BORROW_APPLICATION = 1;

	/****************************************************************************/
	// ORDER_DETAIL_STATUS

	/**
	 * 同意
	 */
	public static final Integer ORDER_DETAIL_STATUS_AGREE = 1;

	/**
	 * 不同意
	 */
	public static final Integer ORDER_DETAIL_STATUS_DISAGREE = 0;
}
