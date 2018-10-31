package com.nb.module.nb.customer.base.book.repository;

import com.nb.module.nb.customer.base.book.domain.TNBBook;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "book")
public interface ITNBBookRepository extends IDataRepository<TNBBook, Integer> {

	TNBBook findOneByCode(String code);

	@Query(value = "select * from t_nb_book where isbn10 = ?1 or isbn13 = ?1", nativeQuery = true)
	TNBBook findOneByISBN(String isbn);

	@Query(value = " select b.* from " +
			" (select book_code, count(*), sum(tag_count) total from t_nb_book_tag where tag_code in :tagCodes group by book_code having count(*) = :size)a, " +
			" t_nb_book b where a.book_code = b.code order by a.total desc \n#pageable\n ",
			countQuery = " select count(*) from " +
					" (select book_code, count(*), sum(tag_count) total from t_nb_book_tag where tag_code in :tagCodes group by book_code having count(*) = :size)a, " +
					" t_nb_book b where a.book_code = b.code ",
			nativeQuery = true)
	Page<TNBBook> findAllByTagCode(@Param("tagCodes") List<String> tagCodes, @Param("size") Integer size, Pageable pageable);

	@Query(value = " select n.* from " +
			" (select distinct b.code from t_nb_book a left join t_nb_author b on a.code = b.code where a.title like :search or a.publisher like :search or a.isbn13 like :search or b.author like :search) m, " +
			" t_nb_book n where m.code = n.code \n#pageable\n",
			countQuery = " select count(*) from " +
					" (select distinct b.code from t_nb_book a left join t_nb_author b on a.code = b.code where a.title like :search or a.publisher like :search or a.isbn13 like :search or b.author like :search) m, " +
					" t_nb_book n where m.code = n.code ",
			nativeQuery = true)
	Page<TNBBook> findAllBySearch(@Param("search") String search, Pageable pageable);

}
