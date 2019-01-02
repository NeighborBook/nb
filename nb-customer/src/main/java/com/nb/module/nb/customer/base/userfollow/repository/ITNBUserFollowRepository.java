package com.nb.module.nb.customer.base.userfollow.repository;

import com.nb.module.nb.customer.base.userfollow.domain.TNBUserFollow;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "userfollow")
public interface ITNBUserFollowRepository extends IDataRepository<TNBUserFollow, Integer> {

	Page<TNBUserFollow> findAllByUserCode(String userCode, Pageable pageable);

	Page<TNBUserFollow> findAllByFollowUserCode(String followUserCode, Pageable pageable);

	TNBUserFollow findOneByUserCodeAndFollowUserCode(String userCode, String followUserCode);

	Long countByUserCode(String userCode);

	Long countByFollowUserCode(String followUserCode);
}
