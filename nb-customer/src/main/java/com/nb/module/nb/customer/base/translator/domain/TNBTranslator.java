package com.nb.module.nb.customer.base.translator.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "t_nb_translator")
@Data
public class TNBTranslator extends DataEntity {

	private String code;

	private String translator;

}
