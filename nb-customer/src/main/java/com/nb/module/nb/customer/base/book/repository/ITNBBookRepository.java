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
	 * 通过用户编号(!=)查询
	 *
	 * @param userCode
	 * @param pageable
	 * @return
	 */
	@Query(value = " select b.* from t_nb_book b where " +
			" exists(select 1 from (select distinct book_code from t_nb_user_book where user_code != :userCode) ub where b.code = ub.book_code) " +
			" order by updated desc ",
			countQuery = " select count(*) from ( " +
					" select b.* from t_nb_book b where " +
					" exists(select 1 from (select distinct book_code from t_nb_user_book where user_code != :userCode) ub where b.code = ub.book_code) " +
					" order by updated desc " +
					" ) countQuery ",
			nativeQuery = true)
	Page<TNBBook> findAllByUserCodeNot(@Param("userCode") String userCode, Pageable pageable);

	/**
	 * 通过用户编号查询
	 *
	 * @param userCode
	 * @param pageable
	 * @return
	 */
	@Query(value = " select b.* from t_nb_book b where " +
			" exists(select 1 from (select distinct book_code from t_nb_user_book where user_code = :userCode) ub where b.code = ub.book_code) " +
			" order by updated desc ",
			countQuery = " select count(*) from ( " +
					" select b.* from t_nb_book b where " +
					" exists(select 1 from (select distinct book_code from t_nb_user_book where user_code = :userCode) ub where b.code = ub.book_code) " +
					" order by updated desc " +
					" ) countQuery ",
			nativeQuery = true)
	Page<TNBBook> findAllByUserCode(@Param("userCode") String userCode, Pageable pageable);

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
	 * 通过是否共享和用户编号(!=)查询
	 *
	 * @param sharable
	 * @param userCode
	 * @param pageable
	 * @return
	 */
	@Query(value = " select b.* from t_nb_book b where " +
			" exists(select 1 from (select distinct book_code from t_nb_user_book where sharable = :sharable and user_code != :userCode) ub where b.code = ub.book_code) " +
			" order by updated desc ",
			countQuery = " select count(*) from ( " +
					" select b.* from t_nb_book b where " +
					" exists(select 1 from (select distinct book_code from t_nb_user_book where sharable = :sharable and user_code != :userCode) ub where b.code = ub.book_code) " +
					" order by updated desc " +
					" ) countQuery ",
			nativeQuery = true)
	Page<TNBBook> findAllBySharableAndUserCodeNot(@Param("sharable") Integer sharable, @Param("userCode") String userCode, Pageable pageable);

	/**
	 * 通过是否共享和用户编号查询
	 *
	 * @param sharable
	 * @param userCode
	 * @param pageable
	 * @return
	 */
	@Query(value = " select b.* from t_nb_book b where " +
			" exists(select 1 from (select distinct book_code from t_nb_user_book where sharable = :sharable and user_code = :userCode) ub where b.code = ub.book_code) " +
			" order by updated desc ",
			countQuery = " select count(*) from ( " +
					" select b.* from t_nb_book b where " +
					" exists(select 1 from (select distinct book_code from t_nb_user_book where sharable = :sharable and user_code = :userCode) ub where b.code = ub.book_code) " +
					" order by updated desc " +
					" ) countQuery ",
			nativeQuery = true)
	Page<TNBBook> findAllBySharableAndUserCode(@Param("sharable") Integer sharable, @Param("userCode") String userCode, Pageable pageable);

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
	 * 通过标签和用户编号(!=)查询
	 *
	 * @param tagCodes
	 * @param size
	 * @param userCode
	 * @param pageable
	 * @return
	 */
	@Query(value = " select b.* from " +
			" (select book_code, count(*), sum(tag_count) total from t_nb_book_tag where tag_code in :tagCodes group by book_code having count(*) = :size)a, " +
			" t_nb_book b, t_nb_user_book ub where a.book_code = b.code and a.book_code = ub.book_code and ub.user_code != :userCode order by a.total desc ",
			countQuery = " select count(*) from ( " +
					" select b.* from " +
					" (select book_code, count(*), sum(tag_count) total from t_nb_book_tag where tag_code in :tagCodes group by book_code having count(*) = :size)a, " +
					" t_nb_book b, t_nb_user_book ub where a.book_code = b.code and a.book_code = ub.book_code and ub.user_code != :userCode order by a.total desc " +
					" ) countQuery ",
			nativeQuery = true)
	Page<TNBBook> findAllByTagCodeAndUserCodeNot(@Param("tagCodes") List<String> tagCodes, @Param("size") Integer size, @Param("userCode") String userCode, Pageable pageable);

	/**
	 * 通过标签和用户编号查询
	 *
	 * @param tagCodes
	 * @param size
	 * @param userCode
	 * @param pageable
	 * @return
	 */
	@Query(value = " select b.* from " +
			" (select book_code, count(*), sum(tag_count) total from t_nb_book_tag where tag_code in :tagCodes group by book_code having count(*) = :size)a, " +
			" t_nb_book b, t_nb_user_book ub where a.book_code = b.code and a.book_code = ub.book_code and ub.user_code = :userCode order by a.total desc ",
			countQuery = " select count(*) from ( " +
					" select b.* from " +
					" (select book_code, count(*), sum(tag_count) total from t_nb_book_tag where tag_code in :tagCodes group by book_code having count(*) = :size)a, " +
					" t_nb_book b, t_nb_user_book ub where a.book_code = b.code and a.book_code = ub.book_code and ub.user_code = :userCode order by a.total desc " +
					" ) countQuery ",
			nativeQuery = true)
	Page<TNBBook> findAllByTagCodeAndUserCode(@Param("tagCodes") List<String> tagCodes, @Param("size") Integer size, @Param("userCode") String userCode, Pageable pageable);

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

	/**
	 * 通过标签，是否共享和用户编号(!=)查询
	 *
	 * @param tagCodes
	 * @param size
	 * @param sharable
	 * @param userCode
	 * @param pageable
	 * @return
	 */
	@Query(value = " select b.* from " +
			" (select book_code, count(*), sum(tag_count) total from t_nb_book_tag bt where tag_code in :tagCodes " +
			" and exists(select 1 from (select distinct book_code from t_nb_user_book where sharable = :sharable and user_code != :userCode) ub where bt.book_code = ub.book_code) " +
			" group by book_code having count(*) = :size)a, " +
			" t_nb_book b where a.book_code = b.code order by a.total desc ",
			countQuery = " select count(*) from ( " +
					" select b.* from " +
					" (select book_code, count(*), sum(tag_count) total from t_nb_book_tag bt where tag_code in :tagCodes " +
					" and exists(select 1 from (select distinct book_code from t_nb_user_book where sharable = :sharable and user_code != :userCode) ub where bt.book_code = ub.book_code) " +
					" group by book_code having count(*) = :size)a, " +
					" t_nb_book b where a.book_code = b.code order by a.total desc " +
					" ) countQuery ",
			nativeQuery = true)
	Page<TNBBook> findAllByTagCodeAndSharableAndUserCodeNot(@Param("tagCodes") List<String> tagCodes, @Param("size") Integer size, @Param("sharable") Integer sharable, @Param("userCode") String userCode, Pageable pageable);

	/**
	 * 通过标签，是否共享和用户编号查询
	 *
	 * @param tagCodes
	 * @param size
	 * @param sharable
	 * @param userCode
	 * @param pageable
	 * @return
	 */
	@Query(value = " select b.* from " +
			" (select book_code, count(*), sum(tag_count) total from t_nb_book_tag bt where tag_code in :tagCodes " +
			" and exists(select 1 from (select distinct book_code from t_nb_user_book where sharable = :sharable and user_code = :userCode) ub where bt.book_code = ub.book_code) " +
			" group by book_code having count(*) = :size)a, " +
			" t_nb_book b where a.book_code = b.code order by a.total desc ",
			countQuery = " select count(*) from ( " +
					" select b.* from " +
					" (select book_code, count(*), sum(tag_count) total from t_nb_book_tag bt where tag_code in :tagCodes " +
					" and exists(select 1 from (select distinct book_code from t_nb_user_book where sharable = :sharable and user_code = :userCode) ub where bt.book_code = ub.book_code) " +
					" group by book_code having count(*) = :size)a, " +
					" t_nb_book b where a.book_code = b.code order by a.total desc " +
					" ) countQuery ",
			nativeQuery = true)
	Page<TNBBook> findAllByTagCodeAndSharableAndUserCode(@Param("tagCodes") List<String> tagCodes, @Param("size") Integer size, @Param("sharable") Integer sharable, @Param("userCode") String userCode, Pageable pageable);

	/***********************************************************************************************************************************************************************/

	/**
	 * 通过搜索框查询
	 *
	 * @param search
	 * @param pageable
	 * @return
	 */
	@Query(value = " select n.* from " +
			" (select distinct a.code from t_nb_book a left join t_nb_author b on a.code = b.code where a.title like :search or a.publisher like :search or a.isbn13 like :search or b.author like :search) m, " +
			" t_nb_book n where m.code = n.code ",
			countQuery = " select count(*) from ( " +
					" select n.* from " +
					" (select distinct a.code from t_nb_book a left join t_nb_author b on a.code = b.code where a.title like :search or a.publisher like :search or a.isbn13 like :search or b.author like :search) m, " +
					" t_nb_book n where m.code = n.code " +
					" ) countQuery ",
			nativeQuery = true)
	Page<TNBBook> findAllBySearch(@Param("search") String search, Pageable pageable);

	/**
	 * 通过搜索框和用户编号(!=)查询
	 *
	 * @param search
	 * @param userCode
	 * @param pageable
	 * @return
	 */
	@Query(value = " select n.* from " +
			" (select distinct a.code from t_nb_book a left join t_nb_author b on a.code = b.code where a.title like :search or a.publisher like :search or a.isbn13 like :search or b.author like :search) m, " +
			" t_nb_book n where m.code = n.code and exists (select distinct book_code from t_nb_user_book where user_code != :userCode) ",
			countQuery = " select count(*) from ( " +
					" select n.* from " +
					" (select distinct a.code from t_nb_book a left join t_nb_author b on a.code = b.code where a.title like :search or a.publisher like :search or a.isbn13 like :search or b.author like :search) m, " +
					" t_nb_book n where m.code = n.code and exists (select distinct book_code from t_nb_user_book where user_code != :userCode) " +
					" ) countQuery ",
			nativeQuery = true)
	Page<TNBBook> findAllBySearchAndUserCodeNot(@Param("search") String search, @Param("userCode") String userCode, Pageable pageable);

	/**
	 * 通过搜索框和用户编号查询
	 *
	 * @param search
	 * @param userCode
	 * @param pageable
	 * @return
	 */
	@Query(value = " select n.* from " +
			" (select distinct a.code from t_nb_book a left join t_nb_author b on a.code = b.code where a.title like :search or a.publisher like :search or a.isbn13 like :search or b.author like :search) m, " +
			" t_nb_book n where m.code = n.code and exists (select distinct book_code from t_nb_user_book where user_code = :userCode) ",
			countQuery = " select count(*) from ( " +
					" select n.* from " +
					" (select distinct a.code from t_nb_book a left join t_nb_author b on a.code = b.code where a.title like :search or a.publisher like :search or a.isbn13 like :search or b.author like :search) m, " +
					" t_nb_book n where m.code = n.code and exists (select distinct book_code from t_nb_user_book where user_code = :userCode) " +
					" ) countQuery ",
			nativeQuery = true)
	Page<TNBBook> findAllBySearchAndUserCode(@Param("search") String search, @Param("userCode") String userCode, Pageable pageable);

	/***********************************************************************************************************************************************************************/

	/**
	 * 通过地址和用户编号(!=)查询
	 *
	 * @param lbsId
	 * @param userCode
	 * @param pageable
	 * @return
	 */
	@Query(value = " select * from t_nb_book where code in ( " +
			" select distinct book_code from t_nb_user_book where user_code in ( " +
			" select user_code from t_nb_user_location where lbs_id in :lbsId and user_code != :userCode " +
			" )) ",
			countQuery = " select count(*) from ( " +
					" select * from t_nb_book where code in ( " +
					" select distinct book_code from t_nb_user_book where user_code in ( " +
					" select user_code from t_nb_user_location where lbs_id in :lbsId and user_code != :userCode " +
					" )) " +
					" ) countQuery ",
			nativeQuery = true)
	Page<TNBBook> findAllByLbsIdAndUserCodeNot(@Param("lbsId") List<String> lbsId, @Param("userCode") String userCode, Pageable pageable);


	/**
	 * 通过地址，是否共享和用户编号(!=)查询
	 *
	 * @param lbsId
	 * @param sharable
	 * @param userCode
	 * @param pageable
	 * @return
	 */
	@Query(value = " select * from t_nb_book where code in ( " +
			" select distinct book_code from t_nb_user_book where user_code in ( " +
			" select user_code from t_nb_user_location where lbs_id in :lbsId and user_code != :userCode " +
			" ) and sharable = :sharable)" +
			" order by updated desc ",
			countQuery = " select count(*) from ( " +
					" select * from t_nb_book where code in ( " +
					" select distinct book_code from t_nb_user_book where user_code in ( " +
					" select user_code from t_nb_user_location where lbs_id in :lbsId and user_code != :userCode " +
					" ) and sharable = :sharable) " +
					" ) countQuery ",
			nativeQuery = true)
	Page<TNBBook> findAllByLbsIdAndSharableAndUserCodeNot(@Param("lbsId") List<String> lbsId, @Param("sharable") Integer sharable, @Param("userCode") String userCode, Pageable pageable);

}
