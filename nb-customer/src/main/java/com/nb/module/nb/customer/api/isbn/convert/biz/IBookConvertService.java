package com.nb.module.nb.customer.api.isbn.convert.biz;


import com.nb.module.nb.customer.api.book.domain.Book;

public interface IBookConvertService {

	Book findOneByISBN(String isbn);

}
