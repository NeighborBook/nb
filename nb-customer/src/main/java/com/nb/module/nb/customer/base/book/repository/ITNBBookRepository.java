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

	/**
	 * 通过code查询
	 *
	 * @param code
	 * @return
	 */
	TNBBook findOneByCode(String code);

	/**
	 * 通过isbn查询
	 *
	 * @param isbn
	 * @return
	 */
	@Query(value = "select * from t_nb_book where isbn10 = ?1 or isbn13 = ?1", nativeQuery = true)
	TNBBook findOneByISBN(String isbn);

	/***********************************************************************************************************************************************************************/

	/**
	 * 通过是否共享查询
	 *
	 * @param sharable
	 * @param pageable
	 * @return
	 */
	@Query(value = " select b.* from t_nb_book b where " +
			" exists(select 1 from (select distinct book_code from t_nb_user_book where sharable = :sharable) ub where b.code = ub.book_code) " +
			" order by updated desc ",
			countQuery = " select count(*) from ( " +
					" select b.* from t_nb_book b where " +
					" exists(select 1 from (select distinct book_code from t_nb_user_book where sharable = :sharable) ub where b.code = ub.book_code) " +
					" order by updated desc " +
					" ) countQuery ",
			nativeQuery = true)
	Page<TNBBook> findAllBySharable(@Param("sharable") Integer sharable, Pageable pageable);

	/**
	 * 通过标签查询
	 *
	 * @param tagCodes
	 * @param size
	 * @param pageable
	 * @return
	 */
	@Query(value = " select b.* from " +
			" (select book_code, count(*), sum(tag_count) total from t_nb_book_tag where tag_code in :tagCodes group by book_code having count(*) = :size)a, " +
			" t_nb_book b where a.book_code = b.code order by a.total desc ",
			countQuery = " select count(*) from ( " +
					" select b.* from " +
					" (select book_code, count(*), sum(tag_count) total from t_nb_book_tag where tag_code in :tagCodes group by book_code having count(*) = :size)a, " +
					" t_nb_book b where a.book_code = b.code order by a.total desc" +
					" ) countQuery ",
			nativeQuery = true)
	Page<TNBBook> findAllByTagCode(@Param("tagCodes") List<String> tagCodes, @Param("size") Integer size, Pageable pageable);

	/**
	 * 通过标签和是否共享查询
	 *
	 * @param tagCodes
	 * @param size
	 * @param sharable
	 * @param pageable
	 * @return
	 */
	@Query(value = " select b.* from " +
			" (select book_code, count(*), sum(tag_count) total from t_nb_book_tag bt where tag_code in :tagCodes " +
			" and exists(select 1 from (select distinct book_code from t_nb_user_book where sharable = :sharable) ub where bt.book_code = ub.book_code) " +
			" group by book_code having count(*) = :size)a, " +
			" t_nb_book b where a.book_code = b.code order by a.total desc ",
			countQuery = " select count(*) from ( " +
					" select b.* from " +
					" (select book_code, count(*), sum(tag_count) total from t_nb_book_tag bt where tag_code in :tagCodes " +
					" and exists(select 1 from (select distinct book_code from t_nb_user_book where sharable = :sharable) ub where bt.book_code = ub.book_code) " +
					" group by book_code having count(*) = :size)a, " +
					" t_nb_book b where a.book_code = b.code order by a.total desc " +
					" ) countQuery ",
			nativeQuery = true)
	Page<TNBBook> findAllByTagCodeAndSharable(@Param("tagCodes") List<String> tagCodes, @Param("size") Integer size, @Param("sharable") Integer sharable, Pageable pageable);

	/***********************************************************************************************************************************************************************/

	/**
	 * 通过搜索框查询
	 *
	 * @param search
	 * @param pageable
	 * @return
	 */
	@Query(value = " select n.* from " +
			" (select distinct b.code from t_nb_book a left join t_nb_author b on a.code = b.code where a.title like :search or a.publisher like :search or a.isbn13 like :search or b.author like :search) m, " +
			" t_nb_book n where m.code = n.code ",
			countQuery = " select count(*) from ( " +
					" select n.* from " +
					" (select distinct b.code from t_nb_book a left join t_nb_author b on a.code = b.code where a.title like :search or a.publisher like :search or a.isbn13 like :search or b.author like :search) m, " +
					" t_nb_book n where m.code = n.code " +
					" ) countQuery ",
			nativeQuery = true)
	Page<TNBBook> findAllBySearch(@Param("search") String search, Pageable pageable);

}
