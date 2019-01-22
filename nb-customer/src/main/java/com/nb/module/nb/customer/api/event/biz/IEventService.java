package com.nb.module.nb.customer.api.event.biz;


import com.nb.module.nb.customer.api.event.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEventService {

	void save(Event event);

	Page<Event> findAll(Pageable pageable);

	Event findOneByCode(String code);

}
