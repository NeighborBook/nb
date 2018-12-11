package com.nb.module.nb.customer.base.partnerauthorization.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_nb_partner_authorization")
@Data
public class TNBPartnerAuthorization extends DataEntity {

	private String code;

	private String username;

	private String password;

	private String purpose;

}
