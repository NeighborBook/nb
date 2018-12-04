package com.nb.module.nb.customer.api.usercheckin.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UserCheckIn {

	@NotBlank
	private String userCode;

	@NotNull
	private Date updated;

	@NotNull
	private Date checkIn;

	private String remark;

}
