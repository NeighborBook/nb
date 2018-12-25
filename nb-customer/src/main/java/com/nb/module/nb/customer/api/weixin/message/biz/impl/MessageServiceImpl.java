package com.nb.module.nb.customer.api.weixin.message.biz.impl;

import com.alibaba.fastjson.JSON;
import com.nb.module.nb.customer.api.weixin.message.biz.IMessageService;
import com.nb.module.nb.customer.api.weixin.token.biz.ITokenService;
import com.nb.module.partner.weixin.client.api.message.biz.IWeixinMessageService;
import com.nb.module.partner.weixin.client.api.message.domain.WeixinMessageTemplate;
import com.nb.module.partner.weixin.client.api.message.domain.WeixinMessageTemplateDataMap;
import com.nb.module.partner.weixin.client.api.message.domain.WeixinMessageTemplateResult;
import com.nb.module.partner.weixin.client.api.message.provider.WeixinMessageTemplateProvider;
import com.nb.module.partner.weixin.client.api.token.domain.AccessToken;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class MessageServiceImpl extends CommonServiceImpl implements IMessageService {

	@Autowired
	private WeixinMessageTemplateProvider provider;

	@Autowired
	private IWeixinMessageService weixinMessageService;
	@Autowired
	private ITokenService tokenService;

	@Value("${weixin.message.bookLendingReminderTemplateId}")
	private String bookLendingReminderTemplateId;

	@Value("${weixin.message.bookLendingReminderTemplateUrl}")
	private String bookLendingReminderTemplateUrl;

	@Value("${weixin.message.bookLendingStatusReminderTemplateId}")
	private String bookLendingStatusReminderTemplateId;

	@Value("${weixin.message.bookLendingStatusReminderTemplateUrl}")
	private String bookLendingStatusReminderTemplateUrl;

	public static final SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public void templateSend(WeixinMessageTemplate template) {
		try {
			AccessToken accessToken = tokenService.accessToken(false);
			WeixinMessageTemplateResult result = weixinMessageService.templateSend(accessToken.getAccessToken(), template);
		} catch (Exception e) {
			// TODO 暂时屏蔽异常
			log.error(e.getMessage(), JSON.toJSONString(template));
		}
	}

	@Override
	public void sendBookLendingReminder(String toUserOpenid, String fromUserName, String bookName, Date startBorrowDate, String orderCode) {
		WeixinMessageTemplateDataMap data = provider.prepareBorrowData("有人向您提出借书申请！", fromUserName, bookName, YYYYMMDD.format(startBorrowDate), "感谢您的分享！请点击进入详情页确认!");
		templateSend(provider.prepareTemplate(toUserOpenid, bookLendingReminderTemplateId, bookLendingReminderTemplateUrl + orderCode, data));
	}

	@Override
	public void sendBookLendingStatusReminder(String toUserOpenid, String bookName, String status, Date date, String orderCode) {
		WeixinMessageTemplateDataMap data = provider.prepareBorrowData("您的图书有了新的动态！", bookName, status, YYYYMMDD.format(date), "请点击进入详情页处理!");
		templateSend(provider.prepareTemplate(toUserOpenid, bookLendingStatusReminderTemplateId, bookLendingStatusReminderTemplateUrl + orderCode, data));
	}
}
