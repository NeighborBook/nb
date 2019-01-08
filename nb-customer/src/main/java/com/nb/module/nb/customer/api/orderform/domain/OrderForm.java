package com.nb.module.nb.customer.api.orderform.domain;

import com.nb.module.nb.customer.api.userbonus.domain.UserBonus;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderForm<T> {

	private Date created;

	private Date updated;

	private String code;

	private String userCode;

	private Integer orderType;

	private Integer orderStatus;

	private List<OrderFormDetail> details = new ArrayList<>();

	private T order;

	private UserBonus userBonus;

	private Long days;

	public OrderForm(Date created, Date updated, String code, String userCode, Integer orderType, Integer orderStatus) {
		this.created = created;
		this.updated = updated;
		this.code = code;
		this.userCode = userCode;
		this.orderType = orderType;
		this.orderStatus = orderStatus;
	}

	public OrderForm(String userCode, Integer orderType, Integer orderStatus) {
		this.userCode = userCode;
		this.orderType = orderType;
		this.orderStatus = orderStatus;
	}

	public OrderForm() {

	}
}
