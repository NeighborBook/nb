package com.nb.module.nb.customer.base.orderborrow.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "t_nb_order_borrow")
@Data
public class TNBOrderBorrow extends DataEntity {

	private String orderCode;

	private String fromUserCode;

	private String bookCode;

	private String toUserCode;

	private Integer bookCount;

	@Column(columnDefinition = "timestamp")
	private Date startBorrowDate;

	@Column(columnDefinition = "timestamp")
	private Date initialReturnDate;

	@Column(columnDefinition = "timestamp")
	private Date expectedReturnDate;

	@Column(columnDefinition = "timestamp")
	private Date actualReturnDate;

}
