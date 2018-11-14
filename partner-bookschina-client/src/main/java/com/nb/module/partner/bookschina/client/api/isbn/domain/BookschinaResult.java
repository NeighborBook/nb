package com.nb.module.partner.bookschina.client.api.isbn.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BookschinaResult {

	private BigDecimal SearchTotalSeconds;

	private Integer PageCount;

	private Integer RecCount;

	private List<BookschinaBook> BookInfoEntityList;

}
