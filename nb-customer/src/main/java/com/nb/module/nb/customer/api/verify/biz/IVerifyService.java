package com.nb.module.nb.customer.api.verify.biz;


import com.zjk.module.common.authorization.client.api.verificationcode.domain.VerificationCodeCheck;

public interface IVerifyService {


	/**
	 * 校验验证码
	 *
	 * @param check
	 * @param keyword
	 */
	void check(VerificationCodeCheck check, String keyword);

	/**
	 * 验证短信
	 *
	 * @param mobile
	 * @return
	 */
	String sms(String mobile, String lang);
}
