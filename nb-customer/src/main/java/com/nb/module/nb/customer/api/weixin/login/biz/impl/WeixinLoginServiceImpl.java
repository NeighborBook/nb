package com.nb.module.nb.customer.api.weixin.login.biz.impl;

import com.nb.module.nb.customer.api.isbn.convert.constant.BookConvertConstant;
import com.nb.module.nb.customer.api.login.biz.impl.LoginCommonServiceImpl;
import com.nb.module.nb.customer.api.login.domain.LoginResult;
import com.nb.module.nb.customer.api.weixin.constant.WeixinLoginConstant;
import com.nb.module.nb.customer.api.weixin.exception.WeixinLoginCode;
import com.nb.module.nb.customer.api.weixin.login.biz.IWeixinLoginService;
import com.nb.module.partner.aliyun.oss.biz.IUploadService;
import com.nb.module.partner.aliyun.oss.path.IPathService;
import com.nb.module.partner.weixin.client.api.image.client.IWeixinImageClient;
import com.nb.module.partner.weixin.client.api.sns.biz.IWeixinSnsService;
import com.nb.module.partner.weixin.client.api.sns.constant.SnsConstant;
import com.nb.module.partner.weixin.client.api.sns.domain.AccessToken;
import com.nb.module.partner.weixin.client.api.sns.domain.WeixinUserInfo;
import com.nb.module.partner.weixin.client.holder.WeixinHolder;
import com.zjk.module.common.authorization.client.api.passport.domain.Register;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import com.zjk.module.common.authorization.client.exception.AuthorizationCode;
import com.zjk.module.common.authorization.client.weixin.plugin.api.passport.constant.WeixinPluginConstant;
import com.zjk.module.common.authorization.client.weixin.plugin.api.passport.domain.UserWeixin;
import com.zjk.module.common.base.exception.BusinessException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;

@Service
@Slf4j
public class WeixinLoginServiceImpl extends LoginCommonServiceImpl implements IWeixinLoginService {

	@Autowired
	private WeixinHolder holder;
	@Autowired
	private IWeixinSnsService weixinSnsService;
	@Autowired
	private IWeixinImageClient weixinImageClient;
	@Autowired
	private IUploadService uploadService;
	@Autowired
	private IPathService pathService;

	@Value("${login.weixin.role}")
	private String role;

	public static final String HDADIMGURL = "headimgurl";

	private static String roleCode;

	@Override
	@Transactional
	public LoginResult login(String username, String type, HttpServletResponse response) {
		LoginResult result;
		if (WeixinLoginConstant.TYPE_OPENID.equalsIgnoreCase(type)) {
			// 登陆
			result = loginSimple(username, response);
		} else if (WeixinLoginConstant.TYPE_CODE.equalsIgnoreCase(type)) {
			// 通过code获取accessToken和openid
			AccessToken accessToken = weixinSnsService.accessToken(holder.getAppId(), holder.getAppSecret(), username, SnsConstant.GRAND_TYPE);
			// 尝试登陆，判断openid是否存在
			try {
				// 登陆
				result = loginSimple(accessToken.getOpenid(), response);
			} catch (BusinessException e) {
				// 如果用户名错误，执行注册
				if (AuthorizationCode.PP0001.getCode().equalsIgnoreCase(e.getCode())) {
					// 注册
					result = register(accessToken, response);
				}
				// 其他错误抛出
				else {
					throw e;
				}
			}
		} else {
			throw new BusinessException(WeixinLoginCode.WXL0001, new Object[]{type});
		}
		return result;
	}

	/**
	 * 登陆
	 *
	 * @param openid
	 * @param response
	 * @return
	 */
	private LoginResult loginSimple(String openid, HttpServletResponse response) {
		// 获取返回结果
		return login(userService.loginSimple(openid, WeixinPluginConstant.WEIXIN_PLUGIN), response);
	}

	/**
	 * 获取登录信息
	 *
	 * @param user
	 * @return
	 */
	protected LoginResult getLoginResult(User user) {
		LoginResult result = new LoginResult(user.getCode(), user.getEmail(), user.getMobile(), user.getEmailVerified(), user.getEmailVerified(), user.getPlugin());
		LinkedHashMap map = (LinkedHashMap) result.getPlugin().get(WeixinPluginConstant.WEIXIN_PLUGIN);
		map.put(HDADIMGURL, pathService.generatePresignedUrl(pathService.getFilename((String) map.get(HDADIMGURL))));
		return result;
	}


	/**
	 * 注册
	 *
	 * @param accessToken
	 * @param response
	 * @return
	 */
	private LoginResult register(AccessToken accessToken, HttpServletResponse response) {
		// 注册
		User user = register(accessToken);
		try {
			// 绑定默认用户角色
			if (StringUtils.isBlank(roleCode)) {
				roleCode = userService.findRoleByRole(role).getCode();
			}
			userService.saveUserRole(user.getCode(), roleCode);
			// 获取返回结果
			return login(user, response);
		} catch (Exception ex) {
			// 出错回滚
			userService.deleteByCode(user.getCode(), WeixinPluginConstant.WEIXIN_PLUGIN);
			throw ex;
		}
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
				url = uploadImage(url, wx.getOpenid());
			} catch (Exception ex) {
				log.warn(BookConvertConstant.UPLOAD_IMAGE_FAILURE + url, ex);
			}
		}
		Register register = new Register();
		register.getPlugin().put(WeixinPluginConstant.WEIXIN_PLUGIN, new UserWeixin(null, wx.getOpenid(), wx.getNickname(), wx.getSex(), wx.getCity(), wx.getCity(), wx.getProvince(), wx.getCountry(), url, wx.getUnionid()));
		return userService.register(register, WeixinPluginConstant.WEIXIN_PLUGIN);
	}

	/**
	 * 上传图片
	 *
	 * @param url
	 * @return
	 */
	@SneakyThrows
	private String uploadImage(String url, String openid) {
		String[] arr = url.split("mmopen/");
		ResponseEntity<byte[]> result = weixinImageClient.image(arr[arr.length - 1]);
		return uploadService.uploadByte(result.getBody(), HDADIMGURL + openid);
	}
}
