package com.nb.module.nb.customer.base.usersharedetail.biz;

import com.nb.module.nb.customer.base.usersharedetail.domain.TNBUserShare;
import com.zjk.module.common.data.biz.IDataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ITNBUserShareService extends IDataService<TNBUserShare, Integer> {

	TNBUserShare newInstance();

	TNBUserShare findOneByCode(String code);

	Page<TNBUserShare> findAllByUserCode(String userCode, Pageable pageable);

	List<TNBUserShare> findAllByUserCodeAndShareDate(String userCode, Date shareDate);

}
