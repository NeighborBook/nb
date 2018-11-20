package com.nb.module.nb.customer.api.verify.biz.impl;

import com.nb.module.nb.customer.api.verify.biz.IVerifyService;
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
//	@Autowired
//	private ISMSClient smsClient;

//	private static final String MAIL_KEYWORD = "mail_{0}";
//	private static final String MAIL_SUBJECT = "Onlineleasing邮件验证码";
//	@Value("${verify.mailTemplateCode}")
//	private String mailTemplateCode;
//	@Value("${verify.mailEnTemplateCode}")
//	private String mailEnTemplateCode;
//
	private static final String SMS_KEYWORD = "sms_{0}";
//	@Value("${verify.smsTemplateCode}")
//	private String smsTemplateCode;
//	@Value("${verify.smsEnTemplateCode}")
//	private String smsEnTemplateCode;

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
		// 发送短信 TODO 后续再考虑总线
		Map<String, String> model = new HashMap<>();
		model.put("verificationcode", verificationCode.getCode());
		// TODO
		if ("en-us".equalsIgnoreCase(lang)) {
			//checkJsonContainer(smsClient.sendByTemplate(new com.sbm.module.common.message.api.sms.domain.SendByTemplate(mobile, null, new Date(), smsEnTemplateCode, model)));
		} else {
			//checkJsonContainer(smsClient.sendByTemplate(new com.sbm.module.common.message.api.sms.domain.SendByTemplate(mobile, null, new Date(), smsTemplateCode, model)));
		}
		log.info(verificationCode.getCode());
		// 返回键
		return verificationCode.getKey();
	}
}
