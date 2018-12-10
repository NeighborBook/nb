package com.nb.module.nb.customer.api.verify.biz.impl;

import com.nb.module.nb.customer.api.verify.biz.IVerifyService;
import com.nb.module.partner.aliyun.dysmsapi.biz.IDySmsApiService;
import com.nb.module.partner.aliyun.dysmsapi.domain.DySmsApi;
import com.zjk.module.common.authorization.client.api.verificationcode.client.IVerificationCodeClient;
import com.zjk.module.common.authorization.client.api.verificationcode.domain.VerificationCode;
import com.zjk.module.common.authorization.client.api.verificationcode.domain.VerificationCodeCheck;
import com.zjk.module.common.authorization.client.api.verificationcode.domain.VerificationCodeSetting;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class VerifyServiceImpl extends CommonServiceImpl implements IVerifyService {

	@Autowired
	private IVerificationCodeClient verificationCodeClient;
	@Autowired
	private IDySmsApiService dySmsApiService;

	private static final String SMS_KEYWORD = "sms_{0}";

	@Value("${verify.signName}")
	private String signName;
	@Value("${verify.smsTemplateCode}")
	private String smsTemplateCode;

	private static String VC = "0123456789";

	@Override
	public void check(VerificationCodeCheck check, String keyword) {
		check.setKeyword(keyword);
		checkJsonContainer(verificationCodeClient.check(check));
	}

	@Override
	public String sms(String mobile, String lang) {
		// 生成验证码
		VerificationCode verificationCode = checkJsonContainer(verificationCodeClient.generate(new VerificationCodeSetting(MessageFormat.format(SMS_KEYWORD, mobile), 6, VC)));
		// 发送短信 TODO 后续再考虑总线，改造成message形式，目前先简单弄
		Map<String, Object> model = new HashMap<>();
		model.put("code", verificationCode.getCode());
		dySmsApiService.sendSMS(new DySmsApi(mobile, signName, smsTemplateCode, model, null));
//		log.info(verificationCode.getCode());
		// 返回键
		return verificationCode.getKey();
	}
}
