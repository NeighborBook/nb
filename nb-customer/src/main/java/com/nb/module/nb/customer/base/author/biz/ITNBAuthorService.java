package com.nb.module.nb.customer.base.author.biz;


import com.nb.module.nb.customer.base.author.domain.TNBAuthor;
import com.zjk.module.common.data.biz.IDataService;

import java.util.List;

public interface ITNBAuthorService extends IDataService<TNBAuthor, Integer> {

	List<TNBAuthor> findAllByCode(String code);

}
