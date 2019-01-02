package com.nb.module.nb.customer.base.userbook.repository;

import com.nb.module.nb.customer.base.userbook.domain.TNBUserBook;
import com.nb.module.nb.customer.base.userbook.domain.TNBUserBookCount;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "userbook")
public interface ITNBUserBookRepository extends IDataRepository<TNBUserBook, Integer> {

	TNBUserBook findOneByUserCodeAndBookCode(String userCode, String bookCode);

	List<TNBUserBook> findAllByUserCodeOrderByBookCountDesc(String userCode);

	Page<TNBUserBook> findAllByBookCodeAndSharable(String bookCode, Integer sharable, Pageable pageable);

	@Query(value = " select new com.nb.module.nb.customer.base.userbook.domain.TNBUserBookCount(ub.userCode, count(ub.id)) from TNBUserBook ub where ub.userCode in ( " +
			" select ul.userCode from TNBUserLocation ul where ul.lbsId in :lbsId and ul.userCode <> :userCode " +
			" ) and ub.sharable = 1 group by ub.userCode order by count(ub.id) desc ")
	Page<TNBUserBookCount> findAllByLbsIdInAndUserCodeNot(@Param("lbsId") List<String> lbsId, @Param("userCode") String userCode, Pageable pageable);

	Long countByUserCode(String userCode);
}
