package com.nb.module.nb.customer.api.weixin.user.biz.impl;

import com.nb.module.nb.customer.api.user.biz.IUserService;
import com.nb.module.nb.customer.api.userbonus.biz.IUserBonusService;
import com.nb.module.nb.customer.api.userbonus.constant.UserBonusConstant;
import com.nb.module.nb.customer.api.userbonus.domain.BaseUserBonus;
import com.nb.module.nb.customer.api.userbonus.domain.UserBonus;
import com.nb.module.nb.customer.api.userbonus.domain.UserBonusTemplate;
import com.nb.module.nb.customer.api.userintro.biz.IUserIntroService;
import com.nb.module.nb.customer.api.userintro.domain.UserIntro;
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
import org.apache.commons.lang3.StringUtils;
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

	@Autowired
	private IUserBonusService userBonusService;
	@Autowired
	private IUserIntroService userIntroService;

	@Override
	public User findOneByCode(String userCode) {
		User user = findFullOneByCode(userCode);
		user.setMobile(null);
		user.setEmail(null);
		return user;
	}

	@Override
	public User findFullOneByCode(String userCode) {
		User user = findFullOneWithPassowrdByCode(userCode);
		user.setPassword(null);
		return user;
	}

	@Override
	public User findFullOneWithPassowrdByCode(String userCode) {
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
		UserBonus userBonus = null;
		// 校验手机
		userService.isNotExistMobile(mobile.getMobile());
		// 查询用户
		User user = findFullOneWithPassowrdByCode(mobile.getUserCode());
		// 手机验证
		if (UserConstant.VERIFIED_MOBILE.equalsIgnoreCase(mobile.getVerificationCodeCheck().getVerifyType())) {
			verifyService.check(mobile.getVerificationCodeCheck(), mobile.getMobile());
			// 如果用户手机原本是null，则认为是注册，送积分
			if (StringUtils.isBlank(user.getMobile()) && null != mobile.getBaseUserBonus()) {
				userBonus = userBonusService.operate(new UserBonusTemplate(mobile.getBaseUserBonus(), UserBonusConstant.USER_BONUS_REGISTER));
				// 如果shareFromUserCode存在，则给邀请人加分，并保存关系
				if (null != mobile.getUserIntro()) {
					saveUserIntro(mobile.getUserIntro());
				}
			}
			user.setMobile(mobile.getMobile());
			user.setMobileVerified(UserConstant.VERIFIED_1);
		}
		// 除此之外报错
		else {
			throw new BusinessException(VerificationCodeErrorCode.VC0004);
		}
		// 只保存默认部分
		user = userService.updateUser(user, null);
		return userBonusService.addUserBonusToUser(user, userBonus);
	}

	private void saveUserIntro(UserIntro userIntro) {
		if (StringUtils.isNotBlank(userIntro.getUserCode()) && StringUtils.isNotBlank(userIntro.getUserCode())) {
			// 保存对象
			userIntroService.save(userIntro);
			// 添加积分
			BaseUserBonus targetBaseUserBonus = userBonusService.findOneBaseUserBonusByUserCode(userIntro.getIntroUserCode());
			targetBaseUserBonus.setBizCode(userIntro.getUserCode());
			userBonusService.operate(new UserBonusTemplate(targetBaseUserBonus, UserBonusConstant.USER_BONUS_INVITE_FRIEND));
		}
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
