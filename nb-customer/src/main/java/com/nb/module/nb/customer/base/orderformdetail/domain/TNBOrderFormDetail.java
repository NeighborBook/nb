package com.nb.module.nb.customer.base.orderformdetail.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_nb_order_form_detail")
@Data
public class TNBOrderFormDetail extends DataEntity {

	private String code;

	private Integer orderDetailType;

	private Integer orderDetailStatus;

	private String remark;
}
