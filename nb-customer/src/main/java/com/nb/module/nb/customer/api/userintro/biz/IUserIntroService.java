package com.nb.module.nb.customer.api.userintro.biz;

import com.nb.module.nb.customer.api.userintro.domain.UserIntro;

public interface IUserIntroService {

	UserIntro findOneByUserCode(String userCode);

	void save(UserIntro userIntro);

}
