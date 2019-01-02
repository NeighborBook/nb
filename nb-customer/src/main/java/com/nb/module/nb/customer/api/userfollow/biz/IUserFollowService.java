package com.nb.module.nb.customer.api.userfollow.biz;

import com.nb.module.nb.customer.api.userfollow.domain.UserFollow;
import com.nb.module.nb.customer.api.userfollow.domain.UserFollowCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserFollowService {

	UserFollowCount count(String userCode);

	Page<UserFollow> findAllByUserCode(String userCode, Pageable pageable);

	Page<UserFollow> findAllByFollowUserCode(String followUserCode, Pageable pageable);

	Boolean isFollow(String userCode, String followUserCode);

	void save(UserFollow userFollow);

	void delete(UserFollow userFollow);
}
