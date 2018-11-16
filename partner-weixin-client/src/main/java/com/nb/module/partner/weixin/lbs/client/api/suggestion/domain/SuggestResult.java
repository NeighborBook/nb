package com.nb.module.partner.weixin.lbs.client.api.suggestion.domain;

import lombok.Data;

import java.util.List;

@Data
public class SuggestResult {

	private Integer status;

	private String message;

	private Integer count;

	private List<SuggestData> data;

}
