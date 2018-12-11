package com.nb.module.nb.customer.api.userintro.biz.impl;

import com.nb.module.nb.customer.api.userintro.biz.IUserIntroService;
import com.nb.module.nb.customer.api.userintro.domain.UserIntro;
import com.nb.module.nb.customer.base.userintro.biz.ITNBUserIntroService;
import com.nb.module.nb.customer.base.userintro.domain.TNBUserIntro;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserIntroServiceImpl extends CommonServiceImpl implements IUserIntroService {

	@Autowired
	private ITNBUserIntroService userIntroService;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public UserIntro findOneByUserCode(String userCode) {
		return mapOneIfNotNull(userIntroService.findOneByUserCode(userCode), e -> convert(e));
	}

	private UserIntro convert(TNBUserIntro e) {
		return new UserIntro(e.getUserCode(), e.getIntroUserCode(), e.getSource());
	}

	@Override
	@Transactional
	public void save(UserIntro userIntro) {
		TNBUserIntro po = userIntroService.findOneByUserCode(userIntro.getUserCode());
		// 如果存在，则不保存
		if (null != po) {
			return;
		}
		po = new TNBUserIntro();
		po.setUserCode(userIntro.getUserCode());
		po.setIntroUserCode(userIntro.getIntroUserCode());
		po.setSource(userIntro.getSource());
		userIntroService.save(po);
	}


}
