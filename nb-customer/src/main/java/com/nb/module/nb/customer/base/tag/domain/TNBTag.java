package com.nb.module.nb.customer.base.tag.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_nb_tag")
@Data
public class TNBTag extends DataEntity {

	private String code;

	private String name;

	private String title;

}
