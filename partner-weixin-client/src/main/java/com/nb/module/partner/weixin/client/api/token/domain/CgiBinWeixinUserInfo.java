package com.nb.module.partner.weixin.client.api.token.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class CgiBinWeixinUserInfo implements Serializable {

	private Integer subscribe;

	private String language;

	private String openid;

	private String nickname;

	private Integer sex;

	private String province;

	private String city;

	private String country;

	private String headimgurl;

	private Date subscribe_time;

	private String remark;

	private Integer groupid;

	private List<String> tagid_list;

	private String subscribe_scene;

	private Integer qr_scene;

	private String qr_scene_str;

}
