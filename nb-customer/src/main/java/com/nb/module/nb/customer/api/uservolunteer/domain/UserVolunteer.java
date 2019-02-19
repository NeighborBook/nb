package com.nb.module.nb.customer.api.uservolunteer.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserVolunteer {

	@NotBlank
	private String code;

	@NotBlank
	private String name;

	@NotBlank
	private String profession;

	@NotBlank
	private String speciality;

	public UserVolunteer(@NotBlank String code, @NotBlank String name, @NotBlank String profession, @NotBlank String speciality) {
		this.code = code;
		this.name = name;
		this.profession = profession;
		this.speciality = speciality;
	}

	public UserVolunteer() {

	}
}
