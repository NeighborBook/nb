package com.nb.module.partner.weixin.client.api.sns.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class WeixinUserInfo implements Serializable {

	private String openid;

	private String nickname;

	private String sex;

	private String province;

	private String city;

	private String country;

	private String headimgurl;

	private String privilege;

	private String unionid;

}
