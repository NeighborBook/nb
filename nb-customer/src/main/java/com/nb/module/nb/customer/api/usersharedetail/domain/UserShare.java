package com.nb.module.nb.customer.api.usersharedetail.domain;

import com.nb.module.nb.customer.api.userbonus.domain.BaseUserBonus;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UserShare {

	@NotNull
	private Date shareDate;

	@Valid
	@NotNull
	private BaseUserBonus baseUserBonus;

}
