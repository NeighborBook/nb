package com.nb.module.nb.customer.api.isbn.biz;


import com.nb.module.nb.customer.api.book.domain.Book;

public interface IISBNService {

	Book findOneByISBN(String isbn);

}
