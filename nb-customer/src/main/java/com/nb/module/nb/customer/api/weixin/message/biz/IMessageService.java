package com.nb.module.nb.customer.api.weixin.message.biz;


import com.nb.module.partner.weixin.client.api.message.domain.WeixinMessageTemplate;

import java.util.Date;

public interface IMessageService {

	void templateSend(WeixinMessageTemplate template);

	void sendBookLendingReminder(String toUserOpenid, String fromUserName, String bookName, Date startBorrowDate);

}
