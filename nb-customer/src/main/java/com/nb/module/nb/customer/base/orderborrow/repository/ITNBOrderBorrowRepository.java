package com.nb.module.nb.customer.base.orderborrow.repository;

import com.nb.module.nb.customer.base.orderborrow.domain.TNBOrderBorrow;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "orderborrow")
public interface ITNBOrderBorrowRepository extends IDataRepository<TNBOrderBorrow, Integer> {

	TNBOrderBorrow findOneByOrderCode(String code);

	@Query(value = "select a.* from t_nb_order_borrow a, t_nb_order_form b where a.order_code = b.code and b.order_type = 1 " +
			" and a.from_user_code = ?1 and a.book_code = ?2, and to_user_code = ?3 " +
			" and b.order_status = ?4 ", nativeQuery = true)
	List<TNBOrderBorrow> findAllByFromUserCodeAndBookCodeAndToUserCodeAndOrderStatus(String fromUserCode, String bookCode, String toUserCode, Integer orderStatus);

}
