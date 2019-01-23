package com.nb.module.nb.customer.base.userevent.repository;

import com.nb.module.nb.customer.base.userevent.domain.TNBUserEvent;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "userevent")
public interface ITNBUserEventRepository extends IDataRepository<TNBUserEvent, Integer> {

	Page<TNBUserEvent> findAllByUserCodeAndStatus(String userCode, Integer status, Pageable pageable);

	Page<TNBUserEvent> findAllByEventCodeAndStatus(String eventCode, Integer status, Pageable pageable);

	Long countByEventCodeAndStatus(String eventCode, Integer status);

	TNBUserEvent findOneByUserCodeAndEventCode(String userCode, String eventCode);

}
