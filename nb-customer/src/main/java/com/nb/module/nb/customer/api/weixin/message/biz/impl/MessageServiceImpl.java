package com.nb.module.nb.customer.api.weixin.message.biz.impl;

import com.nb.module.nb.customer.api.weixin.message.biz.IMessageService;
import com.nb.module.nb.customer.api.weixin.token.biz.ITokenService;
import com.nb.module.partner.weixin.client.api.message.biz.IWeixinMessageService;
import com.nb.module.partner.weixin.client.api.message.domain.WeixinMessageTemplate;
import com.nb.module.partner.weixin.client.api.message.domain.WeixinMessageTemplateDataMap;
import com.nb.module.partner.weixin.client.api.message.domain.WeixinMessageTemplateResult;
import com.nb.module.partner.weixin.client.api.message.provider.WeixinMessageTemplateProvider;
import com.nb.module.partner.weixin.client.api.token.domain.AccessToken;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MessageServiceImpl extends CommonServiceImpl implements IMessageService {

	@Autowired
	private WeixinMessageTemplateProvider provider;

	@Autowired
	private IWeixinMessageService weixinMessageService;
	@Autowired
	private ITokenService tokenService;

	public static final String BOOK_LENDING_REMINDER_TEMPLATE_ID = "-LGNf9vuHBh7qksNK5PtommL9bjDkEYMsD6eiOIquPw";
	public static final String BOOK_LENDING_STATUS_REMINDER_TEMPLATE_ID = "RGHNpgGk5LwDLeGHh25iSuCNGdOm2fcoCl9-Wt_gLdA";


	public static final SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public void templateSend(WeixinMessageTemplate template) {
		AccessToken accessToken = tokenService.accessToken(false);
		WeixinMessageTemplateResult result = weixinMessageService.templateSend(accessToken.getAccessToken(), template);
	}

	@Override
	public void sendBookLendingReminder(String toUserOpenid, String fromUserName, String bookName, Date startBorrowDate, String orderCode) {
		WeixinMessageTemplateDataMap data = provider.prepareBorrowData("有人向您提出借书申请！", fromUserName, bookName, YYYYMMDD.format(startBorrowDate), "感谢您的分享！请点击进入详情页确认!");
		templateSend(provider.prepareTemplate(toUserOpenid, BOOK_LENDING_REMINDER_TEMPLATE_ID, "http://upload.neighborbook.com.cn/order_detail.php?order=" + orderCode, data));
	}

	@Override
	public void sendBookLendingStatusReminder(String toUserOpenid, String bookName, String status, Date date, String orderCode) {
		WeixinMessageTemplateDataMap data = provider.prepareBorrowData("您的图书有了新的动态！", bookName, status, YYYYMMDD.format(date), "请点击进入详情页处理!");
		templateSend(provider.prepareTemplate(toUserOpenid, BOOK_LENDING_STATUS_REMINDER_TEMPLATE_ID, "http://upload.neighborbook.com.cn/order_detail.php?order=" + orderCode, data));
	}
}
