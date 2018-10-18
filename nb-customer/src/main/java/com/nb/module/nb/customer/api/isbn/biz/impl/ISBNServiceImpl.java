package com.nb.module.nb.customer.api.isbn.biz.impl;

import com.nb.module.nb.customer.api.book.biz.IBookService;
import com.nb.module.nb.customer.api.book.domain.Book;
import com.nb.module.nb.customer.api.isbn.biz.IISBNService;
import com.nb.module.nb.customer.api.isbn.convert.biz.IBookConvertService;
import com.nb.module.nb.customer.api.isbn.convert.constant.BookConvertConstant;
import com.nb.module.nb.customer.api.isbn.convert.exception.BookConvertCode;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
import com.zjk.module.common.base.provider.SpringContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ISBNServiceImpl extends CommonServiceImpl implements IISBNService {

	@Autowired
	private SpringContextProvider provider;

	@Autowired
	private IBookService bookService;

	@Override
	public Book findOneByISBN(String isbn) {
		Book book = bookService.findOneByISBN(isbn);
		if (null == book) {
			// 转换书
			book = convert(isbn);
			// 保存书
			bookService.save(book);
			// 生成访问路径
			book.setImage(bookService.generatePresignedUrl(book.getImage()));
		}
		return book;
	}

	/**
	 * 转换
	 *
	 * @param isbn
	 * @return
	 */
	private Book convert(String isbn) {
		Book book = null;
		Map<String, IBookConvertService> map = provider.getBeansOfType(IBookConvertService.class);
		List<Map.Entry<String, IBookConvertService>> list = map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.comparing(e -> {
					Order order = e.getClass().getAnnotation(Order.class);
					return order == null ? 9999 : order.value();
				}
		))).collect(Collectors.toList());
		for (Map.Entry<String, IBookConvertService> stringIBookConvertServiceEntry : list) {
			try {
				book = stringIBookConvertServiceEntry.getValue().findOneByISBN(isbn);
				break;
			} catch (Exception e) {
				log.info(BookConvertConstant.BOOK_NOT_FOUND + stringIBookConvertServiceEntry.getValue().getClass().getSimpleName());
			}
		}
		if (null == book) {
			throw new BusinessException(BookConvertCode.BC0001, new Object[]{isbn});
		}
		return book;
	}


}
