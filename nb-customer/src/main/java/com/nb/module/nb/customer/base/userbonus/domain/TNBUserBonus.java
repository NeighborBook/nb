package com.nb.module.nb.customer.base.userbonus.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "t_nb_user_bonus")
@Data
public class TNBUserBonus extends DataEntity {

	private String userCode;

	@Column(columnDefinition = "decimal(15,0)")
	private BigDecimal totalBonus;

	@Column(columnDefinition = "decimal(15,0)")
	private BigDecimal currentBonus;

}
