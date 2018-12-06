package com.nb.module.nb.customer.api.orderform.constant;

public enum OrderDetailStatusConstant {

	ORDER_DETAIL_STATUS_AGREE(1, "同意"),

	ORDER_DETAIL_STATUS_DENY(0, "不同意");


	private Integer key;

	private String value;

	OrderDetailStatusConstant(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	public Integer getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public static OrderDetailStatusConstant findOneByKey(int key) {
		for (OrderDetailStatusConstant type : values()) {
			if (type.getKey() == key) {
				return type;
			}
		}
		return null;
	}
}
