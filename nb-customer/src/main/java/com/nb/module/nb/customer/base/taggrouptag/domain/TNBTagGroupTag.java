package com.nb.module.nb.customer.base.taggrouptag.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_nb_tag_group_tag")
@Data
public class TNBTagGroupTag extends DataEntity {

	private String tagGroupCode;

	private String tagCode;

	private Integer order;
}
