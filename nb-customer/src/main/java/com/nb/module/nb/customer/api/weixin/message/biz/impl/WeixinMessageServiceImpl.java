package com.nb.module.nb.customer.api.weixin.message.biz.impl;

import com.nb.module.nb.customer.api.weixin.message.biz.IWeixinMessageService;
import com.nb.module.nb.customer.api.weixin.token.biz.ITokenService;
import com.nb.module.partner.weixin.client.api.message.domain.WeixinMessageTemplate;
import com.nb.module.partner.weixin.client.api.message.domain.WeixinMessageTemplateData;
import com.nb.module.partner.weixin.client.api.message.domain.WeixinMessageTemplateResult;
import com.nb.module.partner.weixin.client.api.token.domain.AccessToken;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeixinMessageServiceImpl extends CommonServiceImpl implements IWeixinMessageService {

	@Autowired
	private com.nb.module.partner.weixin.client.api.message.biz.IWeixinMessageService weixinMessageService;
	@Autowired
	private ITokenService tokenService;

	public static final String BOOK_LENDING_REMINDER_TEMPLATE_ID = "-LGNf9vuHBh7qksNK5PtommL9bjDkEYMsD6eiOIquPw";
	public static final String COLOR_173177 = "#173177";
	public static final SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public void templateSend(WeixinMessageTemplate template) {
		AccessToken accessToken = tokenService.accessToken();
		WeixinMessageTemplateResult result = weixinMessageService.templateSend(accessToken.getAccessToken(), template);
	}

	@Override
	public void sendBookLendingReminder(String toUserOpenid, String fromUserName, String bookName, Date startBorrowDate) {
		WeixinMessageTemplate template = new WeixinMessageTemplate();
		template.setTouser(toUserOpenid);
		template.setTemplate_id(BOOK_LENDING_REMINDER_TEMPLATE_ID);
		template.setUrl("http://upload.neighborbook.com.cn/");

		Map<String, WeixinMessageTemplateData> data = new HashMap<>();
		data.put("first", new WeixinMessageTemplateData("有人向您提出借书申请！", COLOR_173177));
		data.put("keyword1", new WeixinMessageTemplateData(fromUserName, COLOR_173177));
		data.put("keyword2", new WeixinMessageTemplateData(bookName, COLOR_173177));
		data.put("keyword3", new WeixinMessageTemplateData(YYYYMMDD.format(startBorrowDate), COLOR_173177));
		data.put("remark", new WeixinMessageTemplateData("感谢您的分享！请点击进入详情页确认!", COLOR_173177));
		template.setData(data);

		templateSend(template);
	}
}
