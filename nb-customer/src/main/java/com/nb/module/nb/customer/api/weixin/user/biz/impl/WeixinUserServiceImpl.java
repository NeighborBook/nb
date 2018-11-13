package com.nb.module.nb.customer.api.weixin.user.biz.impl;

import com.nb.module.nb.customer.api.user.biz.IUserService;
import com.nb.module.nb.customer.api.weixin.constant.WeixinLoginConstant;
import com.nb.module.nb.customer.api.weixin.user.biz.IWeixinUserService;
import com.nb.module.partner.aliyun.oss.path.IPathService;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import com.zjk.module.common.authorization.client.weixin.plugin.api.passport.constant.WeixinPluginConstant;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.LinkedHashMap;

@Service
public class WeixinUserServiceImpl extends CommonServiceImpl implements IWeixinUserService {

	@Autowired
	private IUserService userService;
	@Autowired
	private IPathService pathService;

	@Override
	public User findOneByCode(String userCode) {
		User user = userService.findOneByCode(userCode, WeixinPluginConstant.WEIXIN_PLUGIN);
		if (null != user) {
			user.setMobile(StringUtils.EMPTY);
			user.setEmail(StringUtils.EMPTY);
			user.setPassword(StringUtils.EMPTY);
			LinkedHashMap map = (LinkedHashMap) user.getPlugin().get(WeixinPluginConstant.WEIXIN_PLUGIN);
			map.put(WeixinLoginConstant.HDADIMGURL, pathService.generatePresignedUrl(pathService.getFilename((String) map.get(WeixinLoginConstant.HDADIMGURL)), Calendar.MONTH, 1));
		}
		return user;
	}

	@Override
	public String findOpenidByCode(String userCode) {
		User user = findOneByCode(userCode);
		LinkedHashMap map = (LinkedHashMap) user.getPlugin().get(WeixinPluginConstant.WEIXIN_PLUGIN);
		return (String) map.get(WeixinLoginConstant.OPENID);
	}

	@Override
	public String findNicknameByCode(String userCode) {
		User user = findOneByCode(userCode);
		LinkedHashMap map = (LinkedHashMap) user.getPlugin().get(WeixinPluginConstant.WEIXIN_PLUGIN);
		return (String) map.get(WeixinLoginConstant.NICKNAME);
	}
}
