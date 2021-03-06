package com.nb.module.nb.customer.base.userfollow.biz;

import com.nb.module.nb.customer.base.userfollow.domain.TNBUserFollow;
import com.zjk.module.common.data.biz.IDataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITNBUserFollowService extends IDataService<TNBUserFollow, Integer> {

	Page<TNBUserFollow> findAllByUserCodeOrderByUpdatedDesc(String userCode, Pageable pageable);

	Page<TNBUserFollow> findAllByFollowUserCodeOrderByUpdatedDesc(String followUserCode, Pageable pageable);

	TNBUserFollow findOneByUserCodeAndFollowUserCode(String userCode, String followUserCode);

	Long countByUserCode(String userCode);

	Long countByFollowUserCode(String followUserCode);
}
