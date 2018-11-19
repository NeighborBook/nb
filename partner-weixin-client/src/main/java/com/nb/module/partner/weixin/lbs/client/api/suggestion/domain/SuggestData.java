package com.nb.module.partner.weixin.lbs.client.api.suggestion.domain;

import lombok.Data;

@Data
public class SuggestData {

	private String id;

	private String title;

	private String address;

	private String province;

	private String city;

	private String district;

	private String adcode;

	private Integer type;

	private Integer _distance;

	private SuggestLocation location;

}
