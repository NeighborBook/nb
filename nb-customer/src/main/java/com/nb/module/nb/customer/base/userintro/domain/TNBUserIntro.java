package com.nb.module.nb.customer.base.userintro.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_nb_user_intro")
@Data
public class TNBUserIntro extends DataEntity {

	private String userCode;

	private String introUserCode;

	private String source;

}
