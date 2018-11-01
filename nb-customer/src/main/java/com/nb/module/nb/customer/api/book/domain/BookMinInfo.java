package com.nb.module.nb.customer.api.book.domain;

import com.nb.module.nb.customer.api.tag.domain.BookTag;
import com.nb.module.nb.customer.api.userbook.domain.UserBook;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class BookMinInfo {

	private String code;

	private String subtitle;

	private String pubdate;

	private String image;

	private String publisher;

	private String isbn10;

	private String isbn13;

	private String title;

	private String price;

	private List<Author> authors;

	private List<Translator> translators;

	private List<BookTag> bookTags;

	private Page<UserBook> userBooks;

	public BookMinInfo() {
	}

	public BookMinInfo(Book book) {
		this.code = book.getCode();
		this.subtitle = book.getSubtitle();
		this.pubdate = book.getPubdate();
		this.image = book.getImage();
		this.publisher = book.getPublisher();
		this.isbn10 = book.getIsbn10();
		this.isbn13 = book.getIsbn13();
		this.title = book.getTitle();
		this.price = book.getPrice();
		this.authors = book.getAuthors();
		this.translators = book.getTranslators();
		this.bookTags = book.getBookTags();
		this.userBooks = book.getUserBooks();
	}
}
