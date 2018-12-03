package com.nb.module.nb.customer.base.userbonusdetail.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "t_nb_user_bonus_detail")
@Data
public class TNBUserBonusDetail extends DataEntity {

	private String userCode;

	@Column(columnDefinition = "decimal(15,0)")
	private BigDecimal bonus;

	private Integer type;

	private String remark;

}
