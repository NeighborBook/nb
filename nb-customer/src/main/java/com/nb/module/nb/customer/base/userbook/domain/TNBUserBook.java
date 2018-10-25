package com.nb.module.nb.customer.base.userbook.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_nb_user_book")
@Data
public class TNBUserBook extends DataEntity {

	private String userCode;

	private String bookCode;

	private Integer bookCount;
}
