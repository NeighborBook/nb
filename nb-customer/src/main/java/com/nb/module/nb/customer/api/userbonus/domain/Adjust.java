package com.nb.module.nb.customer.api.userbonus.domain;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class Adjust {

	private BigDecimal extraBonus;

	@Valid
	@NotNull
	private BaseUserBonus baseUserBonus;

}
