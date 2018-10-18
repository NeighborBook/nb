package com.nb.module.nb.customer.base.author.repository;

import com.nb.module.nb.customer.base.author.domain.TNBAuthor;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "author")
public interface ITNBAuthorRepository extends IDataRepository<TNBAuthor, Integer> {

	List<TNBAuthor> findAllByCode(String code);

}
