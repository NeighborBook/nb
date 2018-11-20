package com.nb.module.nb.customer.api.weixin.user.biz.impl;

import com.nb.module.nb.customer.api.user.biz.IUserService;
import com.nb.module.nb.customer.api.verify.biz.IVerifyService;
import com.nb.module.nb.customer.api.weixin.constant.WeixinLoginConstant;
import com.nb.module.nb.customer.api.weixin.user.biz.IWeixinUserService;
import com.nb.module.nb.customer.api.weixin.user.domain.Mobile;
import com.nb.module.partner.aliyun.oss.path.IPathService;
import com.zjk.module.common.authorization.client.api.user.constant.UserConstant;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import com.zjk.module.common.authorization.client.exception.VerificationCodeErrorCode;
import com.zjk.module.common.authorization.client.weixin.plugin.api.passport.constant.WeixinPluginConstant;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.LinkedHashMap;

@Service
public class WeixinUserServiceImpl extends CommonServiceImpl implements IWeixinUserService {

	@Autowired
	private IUserService userService;
	@Autowired
	private IPathService pathService;
	@Autowired
	private IVerifyService verifyService;

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

	@Override
	@Transactional
	public User updateUser(User user) {
		return userService.updateUser(user, WeixinPluginConstant.WEIXIN_PLUGIN);
	}

	@Override
	@Transactional
	public User updateMobile(Mobile mobile) {
		// 校验手机
		userService.isNotExistMobile(mobile.getMobile());
		// 查询用户
		User user = findOneByCode(mobile.getUserCode());
		// 手机验证
		if (UserConstant.VERIFIED_MOBILE.equalsIgnoreCase(mobile.getVerificationCodeCheck().getVerifyType())) {
			// TODO 添加后门，等短信实现后去除
			if (!"6666".equalsIgnoreCase(mobile.getVerificationCodeCheck().getCode())) {
				verifyService.check(mobile.getVerificationCodeCheck(), mobile.getMobile());
			}
			user.setMobileVerified(UserConstant.VERIFIED_1);
		}
		// 除此之外报错
		else {
			throw new BusinessException(VerificationCodeErrorCode.VC0004);
		}
		return updateUser(user);
	}
}
