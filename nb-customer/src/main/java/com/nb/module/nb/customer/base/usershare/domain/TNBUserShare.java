package com.nb.module.nb.customer.base.usershare.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "t_nb_user_share")
@Data
public class TNBUserShare extends DataEntity {

	private String code;

	private String userCode;

	@Column(columnDefinition = "timestamp")
	private Date shareDate;

	private String source;
}
