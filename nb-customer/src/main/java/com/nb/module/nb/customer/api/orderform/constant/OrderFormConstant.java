package com.nb.module.nb.customer.api.orderform.constant;

public class OrderFormConstant {

	/**
	 * 发送给拥有者
	 */
	public static final String SEND_TO_OWNER = "owner";

	/**
	 * 发送给借书人
	 */
	public static final String SEND_TO_BORROWER = "borrower";

	/****************************************************************************/
	// ORDER_OPERATE

	public static final String ORDER_OPERATE_LOCK = "lock";

	public static final String ORDER_OPERATE_UNLOCK = "unlock";

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

}
