package com.nb.module.nb.customer.api.userchildren.biz;


import com.nb.module.nb.customer.api.userchildren.domain.UserChildren;

import java.util.List;

public interface IUserChildrenService {

	void save(UserChildren userChildren);

	List<UserChildren> findAllByUserCode(String userCode);

	UserChildren findOneByCode(String code);

	void delete(String code);
}
