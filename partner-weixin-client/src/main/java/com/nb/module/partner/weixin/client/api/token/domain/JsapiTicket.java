package com.nb.module.partner.weixin.client.api.token.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class JsapiTicket implements Serializable {

	private Integer errcode;

	private String errmsg;

	private String ticket;

	private Long expiresIn;

}
