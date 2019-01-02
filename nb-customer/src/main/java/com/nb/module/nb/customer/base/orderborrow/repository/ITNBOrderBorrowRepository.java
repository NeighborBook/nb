package com.nb.module.nb.customer.base.orderborrow.repository;

import com.nb.module.nb.customer.base.orderborrow.domain.TNBOrderBorrow;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "orderborrow")
public interface ITNBOrderBorrowRepository extends IDataRepository<TNBOrderBorrow, Integer> {

	TNBOrderBorrow findOneByOrderCode(String code);

	@Query(value = "select a.* from t_nb_order_borrow a, t_nb_order_form b where a.order_code = b.code and b.order_type = 1 " +
			" and a.owner_user_code = ?1 and a.book_code = ?2 and borrower_user_code = ?3 " +
			" and b.order_status = ?4 ", nativeQuery = true)
	List<TNBOrderBorrow> findAllByOwnerUserCodeAndBookCodeAndBorrowerUserCodeAndOrderStatus(String ownerUserCode, String bookCode, String borrowerUserCode, Integer orderStatus);

	Page<TNBOrderBorrow> findAllByOwnerUserCode(String ownerUserCode, Pageable pageable);

	Page<TNBOrderBorrow> findAllByBorrowerUserCode(String borrowerUserCode, Pageable pageable);

	@Query(value = "select a.* from t_nb_order_borrow a, t_nb_order_form b where a.order_code = b.code and b.order_type = 1 " +
			" and a.owner_user_code = ?1 " +
			" and b.order_status = ?4 ", nativeQuery = true)
	List<TNBOrderBorrow> findAllByOwnerUserCodeAndOrderStatus(String ownerUserCode, Integer orderStatus);

	@Query(value = "select a.* from t_nb_order_borrow a, t_nb_order_form b where a.order_code = b.code and b.order_type = 1 " +
			" and a.borrower_user_code = ?1 " +
			" and b.order_status = ?4 ", nativeQuery = true)
	List<TNBOrderBorrow> findAllByBorrowerUserCodeAndOrderStatus(String borrowerUserCode, Integer orderStatus);
}
