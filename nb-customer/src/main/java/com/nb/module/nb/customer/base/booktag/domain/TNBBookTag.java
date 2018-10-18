package com.nb.module.nb.customer.base.booktag.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_nb_book_tag")
@Data
public class TNBBookTag extends DataEntity {

	private String bookCode;

	private String tagCode;

	private Integer tagCount;
}
