package com.nb.module.nb.customer.base.usercheckin.repository;

import com.nb.module.nb.customer.base.usercheckin.domain.TNBUserCheckIn;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;

@RepositoryRestResource(path = "usercheckin")
public interface ITNBUserCheckInRepository extends IDataRepository<TNBUserCheckIn, Integer> {

	TNBUserCheckIn findOneByCode(String code);

	Page<TNBUserCheckIn> findAllByUserCode(String userCode, Pageable pageable);

	@Query(value = "select * from t_nb_user_check_in where user_code = ?1 and date_format(updated, '%Y-%m-%d') = date_format(?2, %Y-%m-%d)", nativeQuery = true)
	TNBUserCheckIn findOneByUserCodeAndCheckIn(String userCode, Date checkIn);

}
