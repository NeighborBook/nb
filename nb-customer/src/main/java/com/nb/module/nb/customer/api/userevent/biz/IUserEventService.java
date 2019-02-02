package com.nb.module.nb.customer.api.userevent.biz;

import com.nb.module.nb.customer.api.userevent.domain.UserEvent;
import com.nb.module.nb.customer.api.userevent.domain.UserEventDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserEventService {

	Page<UserEvent> findAllByUserCodeAndStatus(String userCode, Integer status, Pageable pageable);

	Page<UserEvent> findAllByEventCodeAndStatus(String eventCode, Integer status, Pageable pageable);

	Long countByEventCodeAndStatus(String eventCode, Integer status);

	UserEvent findOneByUserCodeAndEventCode(String userCode, String eventCode);

	void signUp(UserEvent userEvent);

	void cancel(UserEvent userEvent);

	UserEventDetail detail(UserEvent userEvent);

}
