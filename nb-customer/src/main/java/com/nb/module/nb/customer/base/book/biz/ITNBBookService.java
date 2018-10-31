package com.nb.module.nb.customer.base.book.biz;

import com.nb.module.nb.customer.base.book.domain.TNBBook;
import com.zjk.module.common.data.biz.IDataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITNBBookService extends IDataService<TNBBook, Integer> {

	TNBBook newInstance();

	TNBBook findOneByCode(String code);

	TNBBook findOneByISBN(String isbn);

	Page<TNBBook> findAllByTagCode(List<String> tagCodes, Integer size, Pageable pageable);

	Page<TNBBook> findAllBySearch(String search, Pageable pageable);
}
