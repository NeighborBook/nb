package com.nb.module.nb.customer.api.orderform.constant;

public enum OrderDetailTypeBorrowConstant {

//	ORDER_DETAIL_TYPE_BORROW_START_BORROW_APPLICATION(1, "发起借书申请", OrderFormConstant.SEND_TO_OWNER),
	ORDER_DETAIL_TYPE_BORROW_BORROW(1, "发起借阅申请", OrderFormConstant.SEND_TO_OWNER),

//	ORDER_DETAIL_TYPE_BORROW_CONFIRM_BORROW_APPLICATION(2, "确认借书申请", OrderFormConstant.SEND_TO_BORROWER),

//	ORDER_DETAIL_TYPE_BORROW_OWNER_DELIVERY(3, "上家发货", OrderFormConstant.SEND_TO_BORROWER),

//	ORDER_DETAIL_TYPE_BORROW_BORROWER_CONFIRM_DELIVERY(4, "下家确认收货", OrderFormConstant.SEND_TO_OWNER),
	ORDER_DETAIL_TYPE_BORROW_BORROW_CONFIRM(4, "借阅", OrderFormConstant.SEND_TO_BORROWER),

//	ORDER_DETAIL_TYPE_BORROW_BORROWER_RENEW(5, "下家续借", OrderFormConstant.SEND_TO_OWNER),
	ORDER_DETAIL_TYPE_BORROW_RENEW(5, "发起续借申请", OrderFormConstant.SEND_TO_OWNER),

//	ORDER_DETAIL_TYPE_BORROW_OWNER_CONFIRM_RENEW(6, "上家确认续借", OrderFormConstant.SEND_TO_BORROWER),
	ORDER_DETAIL_TYPE_BORROW_RENEW_CONFIRM(6, "续借", OrderFormConstant.SEND_TO_BORROWER),

//	ORDER_DETAIL_TYPE_BORROW_BORROWER_RETURN(7, "下家还书", OrderFormConstant.SEND_TO_OWNER),

//	ORDER_DETAIL_TYPE_BORROW_OWNER_CONFIRM_RETURN(8, "上家确认还书", OrderFormConstant.SEND_TO_BORROWER);
	ORDER_DETAIL_TYPE_BORROW_RETURN(8, "归还图书", OrderFormConstant.SEND_TO_OWNER);


	private int key;

	private String value;

	private String sendTo;

	OrderDetailTypeBorrowConstant(int key, String value, String sendTo) {
		this.key = key;
		this.value = value;
		this.sendTo = sendTo;
	}

	public int getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public String getSendTo() {
		return sendTo;
	}

	public static OrderDetailTypeBorrowConstant findOneByKey(int key) {
		for (OrderDetailTypeBorrowConstant type : values()) {
			if (type.getKey() == key) {
				return type;
			}
		}
		return null;
	}

}
