package com.nb.module.nb.customer.base.userfollow.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_nb_user_follow")
@Data
public class TNBUserFollow extends DataEntity {

	private String userCode;

	private String followUserCode;

	private String source;

}
