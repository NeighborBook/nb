package com.nb.module.nb.customer.base.userevent.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_nb_user_event")
@Data
public class TNBUserEvent extends DataEntity {

	private String userCode;

	private String eventCode;

	private Integer status;

}
