package com.nb.module.nb.customer.api.weixin.user.biz.impl;

import com.nb.module.nb.customer.api.user.biz.IUserService;
import com.nb.module.nb.customer.api.weixin.user.biz.IWeixinUserService;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import com.zjk.module.common.authorization.client.weixin.plugin.api.passport.constant.WeixinPluginConstant;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeixinUserServiceImpl extends CommonServiceImpl implements IWeixinUserService {

	@Autowired
	private IUserService userService;

	@Override
	public User findOneByCode(String userCode) {
		User user = userService.findOneByCode(userCode, WeixinPluginConstant.WEIXIN_PLUGIN);
		user.setMobile(StringUtils.EMPTY);
		user.setEmail(StringUtils.EMPTY);
		user.setPassword(StringUtils.EMPTY);
		return user;
	}

}
