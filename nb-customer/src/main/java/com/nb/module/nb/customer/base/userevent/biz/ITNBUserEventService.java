package com.nb.module.nb.customer.base.userevent.biz;

import com.nb.module.nb.customer.base.userevent.domain.TNBUserEvent;
import com.zjk.module.common.data.biz.IDataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITNBUserEventService extends IDataService<TNBUserEvent, Integer> {

	Page<TNBUserEvent> findAllByUserCodeAndStatus(String userCode, Integer status, Pageable pageable);

	Page<TNBUserEvent> findAllByEventCodeAndStatus(String eventCode, Integer status, Pageable pageable);

	Long countByEventCodeAndStatus(String eventCode, Integer status);

	TNBUserEvent findOneByUserCodeAndEventCode(String userCode, String eventCode);
}
