package com.nb.module.nb.customer.base.book.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_nb_book")
@Data
public class TNBBook extends DataEntity {

	private String code;

	private String subtitle;

	private String pubdate;

	private String originTitle;

	@Column(columnDefinition = "text")
	private String image;

	private String binding;

	@Column(columnDefinition = "text")
	private String catalog;

	private String pages;

	@Column(columnDefinition = "text")
	private String alt;

	private String dbId;

	private String publisher;

	private String isbn10;

	private String isbn13;

	private String title;

	@Column(columnDefinition = "text")
	private String url;

	private String altTitle;

	@Column(columnDefinition = "text")
	private String authorIntro;

	@Column(columnDefinition = "text")
	private String summary;

	private String price;

	private String source;

	public TNBBook(String code, String subtitle, String pubdate, String originTitle, String image, String binding, String catalog, String pages, String alt, String dbId, String publisher, String isbn10, String isbn13, String title, String url, String altTitle, String authorIntro, String summary, String price, String source) {
		this.code = code;
		this.subtitle = subtitle;
		this.pubdate = pubdate;
		this.originTitle = originTitle;
		this.image = image;
		this.binding = binding;
		this.catalog = catalog;
		this.pages = pages;
		this.alt = alt;
		this.dbId = dbId;
		this.publisher = publisher;
		this.isbn10 = isbn10;
		this.isbn13 = isbn13;
		this.title = title;
		this.url = url;
		this.altTitle = altTitle;
		this.authorIntro = authorIntro;
		this.summary = summary;
		this.price = price;
		this.source = source;
	}

	public TNBBook() {
	}
}
