package com.nb.module.nb.customer.base.userbook.repository;

import com.nb.module.nb.customer.base.userbook.domain.TNBUserBook;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "userbook")
public interface ITNBUserBookRepository extends IDataRepository<TNBUserBook, Integer> {

	TNBUserBook findOneByUserCodeAndBookCode(String userCode, String bookCode);

	List<TNBUserBook> findAllByUserCodeOrderByBookCountDesc(String userCode);

	List<TNBUserBook> findAllByBookCode(String bookCode);
}