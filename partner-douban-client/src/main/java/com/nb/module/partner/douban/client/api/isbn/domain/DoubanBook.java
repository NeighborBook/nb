package com.nb.module.partner.douban.client.api.isbn.domain;

import lombok.Data;

import java.util.List;

@Data
public class DoubanBook {

	private String subtitle;

	private String pubdate;

	private String originTitle;

	private String image;

	private String binding;

	private String catalog;

	private String pages;

	private String alt;

	private String id;

	private String publisher;

	private String isbn10;

	private String isbn13;

	private String title;

	private String url;

	private String altTitle;

	private String authorIntro;

	private String summary;

	private String price;

	private List<String> author;

	private List<String> translator;

	private List<DoubanBookTag> tags;

}
