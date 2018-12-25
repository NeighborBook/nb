package com.nb.module.nb.customer.base.location.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "t_nb_location")
@Data
public class TNBLocation extends DataEntity {

	private String title;

	private String address;

	private String province;

	private String city;

	private String district;

	private String adcode;

	private Integer type;

	@Column(columnDefinition = "decimal(15,5)")
	private BigDecimal lat;

	@Column(columnDefinition = "decimal(15,5)")
	private BigDecimal lng;

	private String lbsId;
}
