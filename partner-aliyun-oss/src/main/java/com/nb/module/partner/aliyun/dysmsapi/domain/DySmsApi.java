package com.nb.module.partner.aliyun.dysmsapi.domain;

import lombok.Data;

@Data
public class DySmsApi {

	private String mobile;

	private String signName;

	private String templateCode;

	private String templateParam;

	private String outId;
}
