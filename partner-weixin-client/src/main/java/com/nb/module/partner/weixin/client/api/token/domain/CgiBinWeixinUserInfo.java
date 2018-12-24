package com.nb.module.partner.weixin.client.api.token.domain;

import com.nb.module.partner.weixin.client.api.sns.domain.WeixinUserInfo;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CgiBinWeixinUserInfo extends WeixinUserInfo {

	private Integer subscribe;

	private String language;

	private Date subscribe_time;

	private String remark;

	private Integer groupid;

	private List<String> tagid_list;

	private String subscribe_scene;

	private Integer qr_scene;

	private String qr_scene_str;

}
