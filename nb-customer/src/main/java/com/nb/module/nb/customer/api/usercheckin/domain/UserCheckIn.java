package com.nb.module.nb.customer.api.usercheckin.domain;

import com.nb.module.nb.customer.api.userbonus.domain.BaseUserBonus;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UserCheckIn {

	@NotNull
	private Date checkIn;

	@Valid
	@NotNull
	private BaseUserBonus baseUserBonus;

}
