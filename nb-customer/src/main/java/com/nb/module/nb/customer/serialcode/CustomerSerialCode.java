package com.nb.module.nb.customer.serialcode;


import com.zjk.module.common.authorization.client.api.serialcode.constant.ISerialCode;

public enum CustomerSerialCode implements ISerialCode {

	/******************** 权限 ********************/
	NBBOOK("NBBOOK", "书"),
	NBTAG("NBTAG", "标签"),
	NBTAGGROUP("NBTAGGROUP", "标签组"),
	NBORDERFORM("NBORDERFORM", "订单"),
	NBUSERBONUS("NBUSERBONUS", "用户积分"),
	NBUSERCHECKIN("NBUSERCHECKIN", "用户签到"),
	NBUSERSHARE("NBUSERSHARE", "用户分享"),
	NBPARTNERAUTH("NBPARTNERAUTH", "第三方认证"),
	NBCHILDREN("NBCHILDREN", "孩子"),
	NBEVENT("NBEVENT", "活动"),;

	private String serialGroup;

	private String remark;

	CustomerSerialCode(String serialGroup, String remark) {
		this.serialGroup = serialGroup;
		this.remark = remark;
	}

	@Override
	public String getSerialGroup() {
		return serialGroup;
	}

	@Override
	public String getRemark() {
		return remark;
	}
}
