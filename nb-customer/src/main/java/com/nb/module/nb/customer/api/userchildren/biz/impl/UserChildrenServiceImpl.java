package com.nb.module.nb.customer.api.userchildren.biz.impl;

import com.nb.module.nb.customer.api.userchildren.biz.IUserChildrenService;
import com.nb.module.nb.customer.api.userchildren.domain.UserChildren;
import com.nb.module.nb.customer.base.userchildren.biz.ITNBUserChildrenService;
import com.nb.module.nb.customer.base.userchildren.domain.TNBUserChildren;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserChildrenServiceImpl extends CommonServiceImpl implements IUserChildrenService {

	@Autowired
	private ITNBUserChildrenService userChildrenService;


	@Override
	@Transactional
	public void save(UserChildren userChildren) {
		TNBUserChildren po = userChildrenService.findOneByCode(userChildren.getCode());
		if (null == po) {
			po = userChildrenService.newInstance();
		}
		po.setUserCode(userChildren.getUserCode());
		po.setNickname(userChildren.getNickname());
		po.setSex(userChildren.getSex());
		po.setBirthday(userChildren.getBirthday());
		userChildrenService.save(po);
	}

	private UserChildren convert(TNBUserChildren e) {
		return new UserChildren(e.getCode(), e.getUserCode(), e.getNickname(), e.getSex(), e.getBirthday());
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<UserChildren> findAllByUserCode(String userCode) {
		return map(userChildrenService.findAllByUserCode(userCode), e -> convert(e));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public UserChildren findOneByCode(String code) {
		return mapOneIfNotNull(userChildrenService.findOneByCode(code), e -> convert(e));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public void delete(String code) {
		TNBUserChildren po = userChildrenService.findOneByCode(code);
		if (null != po) {
			userChildrenService.delete(po);
		}
	}
}
