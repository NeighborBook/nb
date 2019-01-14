package com.nb.module.nb.customer.api.uservolunteer.biz;


import com.nb.module.nb.customer.api.uservolunteer.domain.UserVolunteer;

public interface IUserVolunteerService {

	void save(UserVolunteer userVolunteer);

	UserVolunteer findOneByCode(String code);

}
