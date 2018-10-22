package com.nb.module.partner.weixin.client.api.sns.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccessToken implements Serializable {

	private String accessToken;

	private Long expiresIn;

	private String refreshToken;

	private String openid;

	private String scope;

}
