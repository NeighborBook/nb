package com.nb.module.nb.customer.base.usershare.repository;

import com.nb.module.nb.customer.base.usershare.domain.TNBUserShare;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(path = "usershare")
public interface ITNBUserShareRepository extends IDataRepository<TNBUserShare, Integer> {

	TNBUserShare findOneByCode(String code);

	Page<TNBUserShare> findAllByUserCode(String userCode, Pageable pageable);

	@Query(value = "select * from t_nb_user_share where user_code = ?1 and date_format(share_date, '%Y-%m-%d') = date_format(?2, '%Y-%m-%d')", nativeQuery = true)
	List<TNBUserShare> findAllByUserCodeAndShareDate(String userCode, Date shareDate);

}
