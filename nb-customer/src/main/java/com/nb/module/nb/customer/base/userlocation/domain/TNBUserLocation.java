package com.nb.module.nb.customer.base.userlocation.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_nb_user_location")
@Data
public class TNBUserLocation extends DataEntity {

	private String userCode;

	private String lbsId;

	private String tagCode;
}
