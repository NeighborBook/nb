package com.nb.module.nb.customer.base.orderform.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_nb_order_form")
@Data
public class TNBOrderForm extends DataEntity {

	private String code;

	private Integer orderType;

	private Integer orderStatus;

}
