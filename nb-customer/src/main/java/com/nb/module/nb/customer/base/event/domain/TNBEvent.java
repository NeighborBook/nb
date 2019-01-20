package com.nb.module.nb.customer.base.event.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "t_nb_event")
@Data
public class TNBEvent extends DataEntity {

	private String code;

	private String name;

	@Column(columnDefinition = "timestamp")
	private Date beginDate;

	@Column(columnDefinition = "timestamp")
	private Date endDate;

	private String location;

	@Column(columnDefinition = "timestamp")
	private Date enterBeginDate;

	@Column(columnDefinition = "timestamp")
	private Date enterEndDate;

	private Integer quota;

	private Integer maxQuota;
}
