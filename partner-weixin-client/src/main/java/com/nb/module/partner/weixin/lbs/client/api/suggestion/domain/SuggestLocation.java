package com.nb.module.partner.weixin.lbs.client.api.suggestion.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SuggestLocation {

	private BigDecimal lat;

	private BigDecimal lng;

}
