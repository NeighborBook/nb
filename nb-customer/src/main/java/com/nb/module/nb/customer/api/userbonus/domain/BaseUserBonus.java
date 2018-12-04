package com.nb.module.nb.customer.api.userbonus.domain;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Validated
public class BaseUserBonus {

	@NotNull
	private Date updated;

	@NotBlank
	private String userCode;

	private String remark;
}
