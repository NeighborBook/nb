package com.nb.module.nb.customer.api.isbn.convert.biz.impl;

import com.alibaba.fastjson.JSON;
import com.nb.module.nb.customer.api.book.domain.Author;
import com.nb.module.nb.customer.api.book.domain.Book;
import com.nb.module.nb.customer.api.isbn.convert.biz.IBookConvertService;
import com.nb.module.nb.customer.api.isbn.convert.constant.BookConvertConstant;
import com.nb.module.nb.customer.api.isbn.convert.exception.BookConvertCode;
import com.nb.module.partner.aliyun.oss.biz.IUploadService;
import com.nb.module.partner.aliyun.oss.path.IPathService;
import com.nb.module.partner.bookschina.client.api.isbn.client.IBookschinaISBNClient;
import com.nb.module.partner.bookschina.client.api.isbn.domain.BookschinaBook;
import com.nb.module.partner.bookschina.client.api.isbn.domain.BookschinaResult;
import com.zjk.module.common.base.adaptor.feign.impl.FeignAdaptorProvider;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Order(2)
public class BookschinaISBNServiclImpl extends CommonServiceImpl implements IBookConvertService {

	@Autowired
	private IBookschinaISBNClient client;
	@Autowired
	private IPathService pathService;
	@Autowired
	private IUploadService uploadService;
	@Autowired
	private FeignAdaptorProvider provider;

	public static final String BOOKSCHINA = "bookschina";

	@Override
	public String source() {
		return BOOKSCHINA;
	}

	@Override
	public Book findOneByISBN(String isbn) {
		BookschinaResult bookschinaResult;
		try {
			String result = client.isbn(isbn);
			bookschinaResult = JSON.parseObject(result, BookschinaResult.class);
			if (null == bookschinaResult.getBookInfoEntityList() || bookschinaResult.getBookInfoEntityList().size() != 1 || StringUtils.isBlank(bookschinaResult.getBookInfoEntityList().get(0).getBOOK_ISBN_INT())) {
				throw new BusinessException(BookConvertCode.BC0001, new Object[]{isbn});
			}
		} catch (Exception e) {
			throw new BusinessException(BookConvertCode.BC0001, e, new Object[]{isbn});
		}
		return convert(bookschinaResult.getBookInfoEntityList().get(0));
	}

	private Book convert(BookschinaBook book) {
		return mapOneIfNotNull(book, e -> {
			String path = e.getBOOK_COVER();
			if (StringUtils.isNotBlank(path) && !path.startsWith("http")) {
				path = "http://www.bookschina.com" + path;
			}
			// 书
			Book tmp = new Book(null, null, e.getPUBLISH_DATE(), null, path, null, null, null,
					null, e.getBOOK_ID(), e.getPUBLISH_NAME(), null, e.getBOOK_ISBN_INT(), e.getBOOK_NAME(), null, null, null,
					null, e.getBOOK_PRICE(), BOOKSCHINA);
			// 上传图片
			try {
				tmp.setImage(uploadImage(path));
			} catch (Exception ex) {
				log.warn(BookConvertConstant.UPLOAD_IMAGE_FAILURE + path, ex);
			}
			// 作者
			List<Author> authors = new ArrayList<>();
			authors.add(new Author(e.getAUTHOR_NAME()));
			tmp.setAuthors(authors);
			// 翻译
			tmp.setTranslators(new ArrayList<>());
			// 标签
			tmp.setBookTags(new ArrayList<>());
			return tmp;
		});
	}

	@SneakyThrows
	private String uploadImage(String path) {
		String filename = pathService.getFilename(path);
		ResponseEntity<byte[]> result = provider.getBytes(path);
		return uploadService.uploadByte(result.getBody(), filename);
	}

}
