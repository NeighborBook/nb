package com.nb.module.nb.customer.base.userintro.biz;

import com.nb.module.nb.customer.base.userintro.domain.TNBUserIntro;
import com.zjk.module.common.data.biz.IDataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITNBUserIntroService extends IDataService<TNBUserIntro, Integer> {

	TNBUserIntro findOneByUserCode(String userCode);

	Page<TNBUserIntro> findAllByIntroUserCode(String introUserCode, Pageable pageable);

}
