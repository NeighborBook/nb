package com.nb.module.partner.weixin.client.api.message.biz.impl;

import com.alibaba.fastjson.JSON;
import com.nb.module.partner.weixin.client.api.message.biz.IWeixinMessageService;
import com.nb.module.partner.weixin.client.api.message.client.IWeixinMessageClient;
import com.nb.module.partner.weixin.client.api.message.domain.WeixinMessageTemplate;
import com.nb.module.partner.weixin.client.api.message.domain.WeixinMessageTemplateResult;
import com.nb.module.partner.weixin.client.exception.WeixinCode;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeixinMessageServiceImpl extends CommonServiceImpl implements IWeixinMessageService {

	@Autowired
	private IWeixinMessageClient client;

	@Override
	public WeixinMessageTemplateResult templateSend(String accessToken, WeixinMessageTemplate template) {
		WeixinMessageTemplateResult weixinMessageTemplateResult;
		String result = client.templateSend(accessToken, template);
		try {
			weixinMessageTemplateResult = JSON.parseObject(result, WeixinMessageTemplateResult.class);
			if (0 != weixinMessageTemplateResult.getErrcode()) {
				throw new BusinessException(WeixinCode.WX0005, null, result);
			}
		} catch (Exception e) {
			throw new BusinessException(WeixinCode.WX0005, e, result);
		}
		return weixinMessageTemplateResult;
	}
}
