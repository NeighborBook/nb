package com.nb.module.partner.aliyun.dysmsapi.domain;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.Map;

@Data
public class DySmsApi {

	private String mobile;

	private String signName;

	private String templateCode;

	private String templateParam;

	private String outId;

	public DySmsApi() {
	}

	public DySmsApi(String mobile, String signName, String templateCode, Map<String, Object> templateParam, String outId) {
		this.mobile = mobile;
		this.signName = signName;
		this.templateCode = templateCode;
		this.templateParam = JSON.toJSONString(templateParam);
		this.outId = outId;
	}

	public DySmsApi(String mobile, String signName, String templateCode, String templateParam, String outId) {
		this.mobile = mobile;
		this.signName = signName;
		this.templateCode = templateCode;
		this.templateParam = templateParam;
		this.outId = outId;
	}
}
