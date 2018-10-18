package com.nb.module.nb.customer.api.book.domain;

import com.nb.module.nb.customer.api.tag.domain.BookTag;
import lombok.Data;

import java.util.List;

@Data
public class Book {

	private String code;

	private String subtitle;

	private String pubdate;

	private String originTitle;

	private String image;

	private String binding;

	private String catalog;

	private String pages;

	private String alt;

	private String dbId;

	private String publisher;

	private String isbn10;

	private String isbn13;

	private String title;

	private String url;

	private String altTitle;

	private String authorIntro;

	private String summary;

	private String price;

	private String source;

	private List<Author> authors;

	private List<Translator> translators;

	private List<BookTag> bookTags;


	public Book() {
	}

	public Book(String code, String subtitle, String pubdate, String originTitle, String image, String binding, String catalog, String pages, String alt, String dbId, String publisher, String isbn10, String isbn13, String title, String url, String altTitle, String authorIntro, String summary, String price, String source) {
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
}
