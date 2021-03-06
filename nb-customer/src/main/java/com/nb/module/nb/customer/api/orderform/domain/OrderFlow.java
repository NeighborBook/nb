package com.nb.module.nb.customer.api.orderform.domain;

import com.nb.module.nb.customer.api.userbonus.domain.BaseUserBonus;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class OrderFlow {

	@NotNull
	private Date updated;

	@NotBlank
	private String orderCode;

	@NotNull
	private Integer orderDetailType;

	@NotNull
	private Integer orderDetailStatus;

	private String remark;

	@Valid
	@NotNull
	private BaseUserBonus baseUserBonus;
}
