package com.nb.module.nb.customer.api.book.biz;


import com.nb.module.nb.customer.api.book.domain.Book;
import com.nb.module.nb.customer.api.book.domain.BookMinInfo;
import com.nb.module.nb.customer.base.book.domain.TNBBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBookService {

	Book findOneByISBN(String isbn);

	Book findOneByCode(String code);

	Book convert(TNBBook e);

	/**
	 * 保存，不执行更新
	 *
	 * @param vo
	 */
	void save(Book vo);

	void update(Book vo);

	Page<BookMinInfo> findAll(Pageable pageable);

	String generatePresignedUrl(String path);

	Page<BookMinInfo> findAllByTagCode(List<String> tagCodes, Integer sharable, Pageable pageable);

	Page<BookMinInfo> findAllByTagCodeAndUserCodeNot(List<String> tagCodes, Integer sharable, String userCode, Pageable pageable);

	Page<BookMinInfo> findAllByTagCodeAndUserCode(List<String> tagCodes, Integer sharable, String userCode, Pageable pageable);

	Page<BookMinInfo> findAllBySearch(String search, Pageable pageable);

	Page<BookMinInfo> findAllBySearchAndUserCodeNot(String search, String userCode, Pageable pageable);

	Page<BookMinInfo> findAllBySearchAndUserCode(String search, String userCode, Pageable pageable);

	Page<BookMinInfo> findAllByLbsIdAndUserCodeNot(List<String> lbsId, Integer sharable, String userCode, Pageable pageable);
}
