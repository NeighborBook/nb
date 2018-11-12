package com.nb.module.nb.customer.api.orderform.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class OrderFlow {

	@NotBlank
	private String orderCode;

	@NotNull
	private Integer orderDetailType;

	@NotNull
	private Integer orderDetailStatus;

	private String remark;

}
