package com.nb.module.nb.customer.api.weixin.user.biz.impl;

import com.nb.module.nb.customer.api.user.biz.IUserService;
import com.nb.module.nb.customer.api.verify.biz.IVerifyService;
import com.nb.module.nb.customer.api.weixin.constant.WeixinLoginConstant;
import com.nb.module.nb.customer.api.weixin.exception.WeixinLoginCode;
import com.nb.module.nb.customer.api.weixin.user.biz.IWeixinUserService;
import com.nb.module.nb.customer.api.weixin.user.domain.Mobile;
import com.nb.module.nb.customer.api.weixin.user.domain.UserLocation;
import com.nb.module.nb.customer.base.userlocation.biz.ITNBUserLocationService;
import com.nb.module.nb.customer.base.userlocation.domain.TNBUserLocation;
import com.nb.module.partner.aliyun.oss.path.IPathService;
import com.zjk.module.common.authorization.client.api.user.constant.UserConstant;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import com.zjk.module.common.authorization.client.exception.VerificationCodeErrorCode;
import com.zjk.module.common.authorization.client.weixin.plugin.api.passport.constant.WeixinPluginConstant;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
	@Autowired
	private ITNBUserLocationService userLocationService;

	@Override
	public User findOneByCode(String userCode) {
		User user = findFullOneByCode(userCode);
		user.setMobile(null);
		user.setEmail(null);
		user.setPassword(null);
		return user;
	}

	@Override
	public User findFullOneByCode(String userCode) {
		User user = checkIfNullThrowException(userService.findOneByCode(userCode, WeixinPluginConstant.WEIXIN_PLUGIN),
				new BusinessException(WeixinLoginCode.WXL0002, new Object[]{userCode}));
		LinkedHashMap map = (LinkedHashMap) user.getPlugin().get(WeixinPluginConstant.WEIXIN_PLUGIN);
		map.put(WeixinLoginConstant.HDADIMGURL, pathService.generatePresignedUrl(pathService.getFilename((String) map.get(WeixinLoginConstant.HDADIMGURL)), Calendar.MONTH, 1));
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
		User user = findFullOneByCode(mobile.getUserCode());
		// 手机验证
		if (UserConstant.VERIFIED_MOBILE.equalsIgnoreCase(mobile.getVerificationCodeCheck().getVerifyType())) {
			// TODO 添加后门，等短信实现后去除
			if (!"6666".equalsIgnoreCase(mobile.getVerificationCodeCheck().getCode())) {
				verifyService.check(mobile.getVerificationCodeCheck(), mobile.getMobile());
			}
			user.setMobile(mobile.getMobile());
			user.setMobileVerified(UserConstant.VERIFIED_1);
		}
		// 除此之外报错
		else {
			throw new BusinessException(VerificationCodeErrorCode.VC0004);
		}
//		return updateUser(user);
		// 只保存默认部分
		return userService.updateUser(user, null);
	}

	@Override
	@Transactional
	public void saveUserLocation(UserLocation userLocation) {
		// 保存userLocation
		TNBUserLocation po = userLocationService.findOneByUserCode(userLocation.getUserCode());
		if (null == po) {
			po = new TNBUserLocation();
			po.setUserCode(userLocation.getUserCode());
		}
		po.setTitle(userLocation.getTitle());
		po.setAddress(userLocation.getAddress());
		po.setProvince(userLocation.getProvince());
		po.setCity(userLocation.getCity());
		po.setDistrict(userLocation.getDistrict());
		po.setAdcode(userLocation.getAdcode());
		po.setType(userLocation.getType());
		po.setLat(userLocation.getLat());
		po.setLng(userLocation.getLng());
		po.setLbsId(userLocation.getLbsId());
		userLocationService.save(po);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public UserLocation findUserLocationByCode(String userCode) {
		return mapOneIfNotNull(userLocationService.findOneByUserCode(userCode),
				e -> new UserLocation(e.getUserCode(), e.getTitle(), e.getAddress(), e.getProvince(), e.getCity(), e.getDistrict(), e.getAdcode(), e.getType(), e.getLat(), e.getLng(), e.getLbsId()));
	}
}
