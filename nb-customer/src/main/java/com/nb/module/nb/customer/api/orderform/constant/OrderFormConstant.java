package com.nb.module.nb.customer.api.orderform.constant;

public class OrderFormConstant {

	/****************************************************************************/
	// ORDER_TYPE

	/**
	 * 借书
	 */
	public static final int ORDER_TYPE_BORROW = 1;

	/****************************************************************************/
	// ORDER_STATUS

	/**
	 * 订单取消
	 */
	public static final int ORDER_STATUS_CANCEL = 0;

	/**
	 * 订单开始
	 */
	public static final int ORDER_STATUS_START = 1;

	/**
	 * 订单结束
	 */
	public static final int ORDER_STATUS_END = 2;

	/****************************************************************************/
	// ORDER_DETAIL_TYPE

	/**
	 * 发起借书申请
	 */
	public static final int ORDER_DETAIL_TYPE_START_BORROW_APPLICATION = 1;

	/**
	 * 确认借书申请
	 */
	public static final int ORDER_DETAIL_TYPE_CONFIRM_BORROW_APPLICATION = 2;

	/**
	 * 上家发货
	 */
	public static final int ORDER_DETAIL_TYPE_OWNER_DELIVERY = 3;

	/**
	 * 下家确认收货
	 */
	public static final int ORDER_DETAIL_TYPE_BORROWER_CONFIRM_DELIVERY = 4;

	/**
	 * 下家续借
	 */
	public static final int ORDER_DETAIL_TYPE_BORROWER_RENEW = 5;

	/**
	 * 上家确认续借
	 */
	public static final int ORDER_DETAIL_TYPE_OWNER_CONFIRM_RENEW = 6;

	/**
	 * 下家还书
	 */
	public static final int ORDER_DETAIL_TYPE_BORROWER_RETURN = 7;

	/**
	 * 上家确认还书
	 */
	public static final int ORDER_DETAIL_TYPE_OWNER_CONFIRM_RETURN = 8;

	/****************************************************************************/
	// ORDER_DETAIL_STATUS

	/**
	 * 同意
	 */
	public static final int ORDER_DETAIL_STATUS_AGREE = 1;

	/**
	 * 不同意
	 */
	public static final int ORDER_DETAIL_STATUS_DISAGREE = 0;
}
