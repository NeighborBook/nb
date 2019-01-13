package com.nb.module.nb.customer.base.userchildren.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "t_nb_user_children")
@Data
public class TNBUserChildren extends DataEntity {

	private String code;

	private String userCode;

	private String nickname;

	private Integer sex;

	@Column(columnDefinition = "timestamp")
	private Date birthday;
}
