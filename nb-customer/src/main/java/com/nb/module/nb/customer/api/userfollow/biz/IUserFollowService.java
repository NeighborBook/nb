package com.nb.module.nb.customer.api.userfollow.biz;

import com.nb.module.nb.customer.api.userfollow.domain.UserFollow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserFollowService {

	Page<UserFollow> findAllByUserCode(String userCode, Pageable pageable);

	Page<UserFollow> findAllByFollowUserCode(String followUserCode, Pageable pageable);

	UserFollow findOneByUserCodeAndFollowUserCode(String userCode, String followUserCode);

	void save(UserFollow userFollow);

	void delete(UserFollow userFollow);
}
