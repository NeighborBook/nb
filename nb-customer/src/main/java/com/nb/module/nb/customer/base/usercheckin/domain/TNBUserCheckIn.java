package com.nb.module.nb.customer.base.usercheckin.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "t_nb_user_check_in")
@Data
public class TNBUserCheckIn extends DataEntity {

	private String code;

	private String userCode;

	@Column(columnDefinition = "timestamp")
	private Date checkIn;

}
