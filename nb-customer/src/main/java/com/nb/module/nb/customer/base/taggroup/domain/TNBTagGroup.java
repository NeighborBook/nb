package com.nb.module.nb.customer.base.taggroup.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_nb_tag_group")
@Data
public class TNBTagGroup extends DataEntity {

	private String code;

	private String name;

	private String title;

	private Integer visible;

	private Integer order;

}
