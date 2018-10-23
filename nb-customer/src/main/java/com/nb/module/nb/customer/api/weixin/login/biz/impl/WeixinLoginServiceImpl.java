package com.nb.module.nb.customer.api.weixin.login.biz.impl;

import com.nb.module.nb.customer.api.isbn.convert.constant.BookConvertConstant;
import com.nb.module.nb.customer.api.login.domain.LoginResult;
import com.nb.module.nb.customer.api.user.biz.IUserService;
import com.nb.module.nb.customer.api.weixin.login.biz.IWeixinLoginService;
import com.nb.module.partner.aliyun.oss.biz.IUploadService;
import com.nb.module.partner.weixin.client.api.image.client.IWeixinImageClient;
import com.nb.module.partner.weixin.client.api.sns.biz.IWeixinSnsService;
import com.nb.module.partner.weixin.client.api.sns.constant.SnsConstant;
import com.nb.module.partner.weixin.client.api.sns.domain.AccessToken;
import com.nb.module.partner.weixin.client.api.sns.domain.WeixinUserInfo;
import com.nb.module.partner.weixin.client.holder.WeixinHolder;
import com.zjk.module.common.authorization.client.api.jsonwebtoken.client.IJSONWebTokenClient;
import com.zjk.module.common.authorization.client.api.jsonwebtoken.constant.JSONWebTokenConstant;
import com.zjk.module.common.authorization.client.api.jsonwebtoken.domain.JSONWebToken;
import com.zjk.module.common.authorization.client.api.passport.domain.Register;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import com.zjk.module.common.authorization.client.exception.AuthorizationCode;
import com.zjk.module.common.authorization.client.weixin.plugin.api.passport.constant.WeixinPluginConstant;
import com.zjk.module.common.authorization.client.weixin.plugin.api.passport.domain.UserWeixin;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.domain.JsonContainer;
import com.zjk.module.common.base.exception.BusinessException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class WeixinLoginServiceImpl extends CommonServiceImpl implements IWeixinLoginService {

	@Autowired
	private IJSONWebTokenClient jsonWebTokenClient;
	@Autowired
	private IUserService userService;

	@Autowired
	private WeixinHolder holder;
	@Autowired
	private IWeixinSnsService weixinSnsService;
	@Autowired
	private IWeixinImageClient weixinImageClient;
	@Autowired
	private IUploadService uploadService;

	@Override
	public LoginResult login(String code, HttpServletResponse response) {
		LoginResult result;
		// 通过code获取accessToken和openid
		AccessToken accessToken = weixinSnsService.accessToken(holder.getAppId(), holder.getAppSecret(), code, SnsConstant.GRAND_TYPE);
		User user;
		// 尝试登陆，判断openid是否存在
		try {
			// 登陆
			user = userService.loginSimple(accessToken.getOpenid(), WeixinPluginConstant.WEIXIN_PLUGIN);
			// 获取返回结果
			result = login(user, response);
		} catch (BusinessException e) {
			// 如果用户名错误，执行注册
			if (AuthorizationCode.PP0001.getCode().equalsIgnoreCase(e.getCode())) {
				// 注册
				user = register(accessToken);
				try {
					// 获取返回结果
					result = login(user, response);
				} catch (Exception ex) {
					// 出错回滚
					userService.deleteByCode(user.getCode(), WeixinPluginConstant.WEIXIN_PLUGIN);
					throw e;
				}
			}
			// 其他错误抛出
			else {
				throw e;
			}
		}
		return result;
	}

	/**
	 * 登录
	 *
	 * @param user
	 * @param response
	 * @return
	 */
	private LoginResult login(User user, HttpServletResponse response) {
		// 获取登录信息
		LoginResult login = getLoginResult(user);
		// 修改最后登陆时间
		userService.updateLastLogin(user.getCode());
		// 写入头参数
		setHeader(response, user);
		return login;
	}

	/**
	 * 获取登录信息
	 *
	 * @param user
	 * @return
	 */
	private LoginResult getLoginResult(User user) {
		LoginResult login = new LoginResult(user.getCode(), user.getEmail(), user.getMobile(), user.getEmailVerified(), user.getEmailVerified(), user.getPlugin());
		return login;
	}

	/**
	 * 写入头参数
	 *
	 * @param response
	 * @param user
	 */
	private void setHeader(HttpServletResponse response, User user) {
		// 写入头参数
		JsonContainer<String> token = jsonWebTokenClient.token(new JSONWebToken(user.getCode()));
		response.setHeader(JSONWebTokenConstant.AUTHORIZATION, checkJsonContainer(token));
		response.setHeader(JSONWebTokenConstant.LOGIN, user.getCode());
	}

	/**
	 * 注册
	 *
	 * @param accessToken
	 * @return
	 */
	private User register(AccessToken accessToken) {
		// 获取微信信息
		WeixinUserInfo wx = weixinSnsService.getUserInfo(accessToken.getAccessToken(), accessToken.getOpenid(), SnsConstant.LANG);
		// 头像url
		String url = wx.getHeadimgurl();
		if (StringUtils.isNotBlank(url)) {
			// 上传图片
			try {
				url = uploadImage(url);
			} catch (Exception ex) {
				log.warn(BookConvertConstant.UPLOAD_IMAGE_FAILURE + url, ex);
			}
		}
		Register register = new Register();
		register.getPlugin().put(WeixinPluginConstant.WEIXIN_PLUGIN, new UserWeixin(null, wx.getOpenid(), wx.getNickname(), wx.getSex(), wx.getCity(), wx.getCity(), wx.getProvince(), wx.getCountry(), url, wx.getUnionid()));
		return userService.register(register, WeixinPluginConstant.WEIXIN_PLUGIN);
	}

	@SneakyThrows
	private String uploadImage(String url) {
		String[] arr = url.split("/");
		ResponseEntity<byte[]> result = weixinImageClient.image(arr[arr.length - 2], arr[arr.length - 1]);
		return uploadService.uploadByte(result.getBody(), arr[arr.length - 2]);
	}
}
