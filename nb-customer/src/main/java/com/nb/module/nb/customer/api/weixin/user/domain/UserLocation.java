package com.nb.module.nb.customer.api.weixin.user.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class UserLocation {

	@NotBlank
	private String userCode;

	private String title;

	private String address;

	private String province;

	private String city;

	private String district;

	private String adcode;

	private Integer type;

	private BigDecimal lat;

	private BigDecimal lng;

	private String lbsId;

	public UserLocation(@NotBlank String userCode, String title, String address, String province, String city, String district, String adcode, Integer type, BigDecimal lat, BigDecimal lng, String lbsId) {
		this.userCode = userCode;
		this.title = title;
		this.address = address;
		this.province = province;
		this.city = city;
		this.district = district;
		this.adcode = adcode;
		this.type = type;
		this.lat = lat;
		this.lng = lng;
		this.lbsId = lbsId;
	}

	public UserLocation() {

	}
}
