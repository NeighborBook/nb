package com.nb.module.nb.customer.api.userbook.biz.impl;

import com.nb.module.nb.customer.api.userbook.biz.IUserBookExpireService;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserBookExpireServiceImpl extends CommonServiceImpl implements IUserBookExpireService {

	@Override
	@Scheduled(cron = "${sync.cron.api.userbookexpire}")
	public void refresh() {
		log.info("执行userbookexpire");
	}

}
