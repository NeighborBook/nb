package com.nb.module.nb.customer.base.uservolunteer.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_nb_user_volunteer")
@Data
public class TNBUserVolunteer extends DataEntity {

	private String code;

	private String speciality;

}
