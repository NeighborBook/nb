package com.nb.module.nb.customer.api.userchildren.domain;

import lombok.Data;

import java.util.Date;

@Data
public class UserChildren {

	private String code;

	private String userCode;

	private String nickname;

	private Integer sex;

	private Date birthday;

	public UserChildren(String code, String userCode, String nickname, Integer sex, Date birthday) {
		this.code = code;
		this.userCode = userCode;
		this.nickname = nickname;
		this.sex = sex;
		this.birthday = birthday;
	}

	public UserChildren() {

	}
}
