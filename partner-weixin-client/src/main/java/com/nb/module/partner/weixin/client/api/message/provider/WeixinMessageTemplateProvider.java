package com.nb.module.partner.weixin.client.api.message.provider;

import com.nb.module.partner.weixin.client.api.message.domain.WeixinMessageTemplate;
import com.nb.module.partner.weixin.client.api.message.domain.WeixinMessageTemplateData;
import com.nb.module.partner.weixin.client.api.message.domain.WeixinMessageTemplateDataMap;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WeixinMessageTemplateProvider {

	public WeixinMessageTemplate prepareTemplate(String toUserOpenid, String templateId, String url, Map<String, WeixinMessageTemplateData> data) {
		WeixinMessageTemplate template = new WeixinMessageTemplate();
		template.setTouser(toUserOpenid);
		template.setTemplate_id(templateId);
		template.setUrl(url);
		template.setData(data);
		return template;
	}

	public WeixinMessageTemplateDataMap prepareBorrowData(String first, String keyword1, String keyword2, String keyword3, String remark) {
		WeixinMessageTemplateDataMap data = new WeixinMessageTemplateDataMap();
		data.put("first", first);
		data.put("keyword1", keyword1);
		data.put("keyword2", keyword2);
		data.put("keyword3", keyword3);
		data.put("remark", remark);
		return data;
	}

}
