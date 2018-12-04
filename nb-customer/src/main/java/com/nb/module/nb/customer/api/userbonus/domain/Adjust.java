package com.nb.module.nb.customer.api.userbonus.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Adjust {

	@NotNull
	private Date updated;

	private String userCode;

	private BigDecimal extraBonus;

	private String remark;

}
