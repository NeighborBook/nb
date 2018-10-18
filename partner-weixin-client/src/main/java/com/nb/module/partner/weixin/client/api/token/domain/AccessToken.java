package com.nb.module.partner.weixin.client.api.token.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccessToken implements Serializable {

	private String accessToken;

	private Long expiresIn;

}
