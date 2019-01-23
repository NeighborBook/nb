package com.nb.module.nb.customer.api.weixin.user.biz.impl;

import com.nb.module.nb.customer.api.user.biz.IUserService;
import com.nb.module.nb.customer.api.userbonus.biz.IUserBonusService;
import com.nb.module.nb.customer.api.userbonus.constant.UserBonusConstant;
import com.nb.module.nb.customer.api.userbonus.domain.UserBonus;
import com.nb.module.nb.customer.api.userbonus.domain.UserBonusTemplate;
import com.nb.module.nb.customer.api.userintro.biz.IUserIntroService;
import com.nb.module.nb.customer.api.userintro.domain.UserIntro;
import com.nb.module.nb.customer.api.verify.biz.IVerifyService;
import com.nb.module.nb.customer.api.weixin.constant.WeixinLoginConstant;
import com.nb.module.nb.customer.api.weixin.exception.WeixinCode;
import com.nb.module.nb.customer.api.weixin.exception.WeixinLoginCode;
import com.nb.module.nb.customer.api.weixin.user.biz.IWeixinUserService;
import com.nb.module.nb.customer.api.weixin.user.domain.Location;
import com.nb.module.nb.customer.api.weixin.user.domain.Mobile;
import com.nb.module.nb.customer.api.weixin.user.domain.UserLocation;
import com.nb.module.nb.customer.base.location.biz.ITNBLocationService;
import com.nb.module.nb.customer.base.location.domain.TNBLocation;
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
import java.util.List;

@Service
public class WeixinUserServiceImpl extends CommonServiceImpl implements IWeixinUserService {

	@Autowired
	private IUserService userService;
	@Autowired
	private IPathService pathService;
	@Autowired
	private IVerifyService verifyService;
	@Autowired
	private ITNBLocationService locationService;
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
				// 保存邀请人信息
				saveUserIntro(mobile.getUserIntro());
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

	@Override
	@Transactional
	public void updateNameAndProfession(String code, String name, String profession) {
		userService.updateNameAndProfession(code, name, profession);
	}

	private void saveUserIntro(UserIntro userIntro) {
		// 如果UserIntro存在，则给邀请人加分，并保存关系
		if (null != userIntro) {
			// userCode和introUserCode不为空
			if (StringUtils.isNotBlank(userIntro.getUserCode()) && StringUtils.isNotBlank(userIntro.getIntroUserCode())) {
				// 保存对象
				userIntroService.save(userIntro);
				// 添加积分，基于微信政策，邀请好友不加分
//				BaseUserBonus targetBaseUserBonus = userBonusService.findOneBaseUserBonusByUserCode(userIntro.getIntroUserCode());
//				targetBaseUserBonus.setBizCode(userIntro.getUserCode());
//				userBonusService.operate(new UserBonusTemplate(targetBaseUserBonus, UserBonusConstant.USER_BONUS_INVITE_FRIEND));
			}
		}
	}

	@Override
	@Transactional
	public void saveUserLocation(UserLocation userLocation) {
		TNBUserLocation po = userLocationService.findOneByUserCodeAndLbsIdAndTagCode(userLocation.getUserCode(), userLocation.getLocation().getLbsId(), userLocation.getTagCode());
		if (null != po) {
			// 如果存在则报错
			throw new BusinessException(WeixinCode.WX0001, new Object[]{userLocation.getUserCode(), userLocation.getLocation().getLbsId(), userLocation.getTagCode()});
		}
		// 保存地址
		saveLocation(userLocation.getLocation());
		// 保存userLocation
		po = new TNBUserLocation();
		po.setUserCode(userLocation.getUserCode());
		po.setLbsId(userLocation.getLocation().getLbsId());
		po.setTagCode(userLocation.getTagCode());
		userLocationService.save(po);
	}

	private void saveLocation(Location location) {
		TNBLocation po = locationService.findOneByLbsId(location.getLbsId());
		if (null != po) {
			// 已存在信息暂不修改
			return;
		}
		po = new TNBLocation();
		po.setTitle(location.getTitle());
		po.setAddress(location.getAddress());
		po.setProvince(location.getProvince());
		po.setCity(location.getCity());
		po.setDistrict(location.getDistrict());
		po.setAdcode(location.getAdcode());
		po.setType(location.getType());
		po.setLat(location.getLat());
		po.setLng(location.getLng());
		po.setLbsId(location.getLbsId());
		locationService.save(po);
	}

	@Override
	@Transactional
	public void deleteUserLocation(UserLocation userLocation) {
		TNBUserLocation po = userLocationService.findOneByUserCodeAndLbsIdAndTagCode(userLocation.getUserCode(), userLocation.getLocation().getLbsId(), userLocation.getTagCode());
		if (null != po) {
			userLocationService.delete(po);
		}
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<UserLocation> findUserLocationByCode(String userCode) {
		return map(userLocationService.findAllByUserCode(userCode), e -> convert(e));
	}

	private UserLocation convert(TNBUserLocation e) {
		Location location = mapOneIfNotNull(locationService.findOneByLbsId(e.getLbsId()), s -> new Location(s.getTitle(), s.getAddress(), s.getProvince(), s.getCity(), s.getDistrict(), s.getAdcode(), s.getType(), s.getLat(), s.getLng(), s.getLbsId()));
		UserLocation userLocation = new UserLocation(e.getUserCode(), location, e.getTagCode());
		return userLocation;
	}
}
