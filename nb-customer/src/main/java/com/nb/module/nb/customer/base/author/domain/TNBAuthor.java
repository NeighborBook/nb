package com.nb.module.nb.customer.base.author.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_nb_author")
@Data
public class TNBAuthor extends DataEntity {

	private String code;

	private String author;

}
