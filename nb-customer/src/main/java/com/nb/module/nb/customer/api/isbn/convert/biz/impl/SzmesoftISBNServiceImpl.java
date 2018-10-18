package com.nb.module.nb.customer.api.isbn.convert.biz.impl;

import com.alibaba.fastjson.JSON;
import com.nb.module.nb.customer.api.book.domain.Author;
import com.nb.module.nb.customer.api.book.domain.Book;
import com.nb.module.nb.customer.api.isbn.convert.biz.IBookConvertService;
import com.nb.module.nb.customer.api.isbn.convert.constant.BookConvertConstant;
import com.nb.module.nb.customer.api.isbn.convert.exception.BookConvertCode;
import com.nb.module.partner.aliyun.oss.biz.IUploadService;
import com.nb.module.partner.szmesoft.client.api.client.ISzmesoftImageClient;
import com.nb.module.partner.szmesoft.client.api.isbn.client.ISzmesoftISBNClient;
import com.nb.module.partner.szmesoft.client.api.isbn.domain.SzmesoftBook;
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
public class SzmesoftISBNServiceImpl extends CommonServiceImpl implements IBookConvertService {

	@Autowired
	private ISzmesoftISBNClient client;
	@Autowired
	private ISzmesoftImageClient imageClient;
	@Autowired
	private IUploadService uploadService;

	public static final String SZMESOFT = "szmesoft";

	public static final String URL = "http://isbn.szmesoft.com/ISBN/GetBookPhoto?ID=";

	@Override
	public Book findOneByISBN(String isbn) {
		SzmesoftBook book;
		try {
			String result = client.isbn(isbn);
			book = JSON.parseObject(result, SzmesoftBook.class);
			if (StringUtils.isBlank(book.getIsbn())) {
				throw new BusinessException(BookConvertCode.BC0001, new Object[]{isbn});
			}
		} catch (Exception e) {
			throw new BusinessException(BookConvertCode.BC0001, e, new Object[]{isbn});
		}
		return convert(book);
	}

	private Book convert(SzmesoftBook book) {
		return mapOneIfNotNull(book, e -> {
			// 书
			Book tmp = new Book(null, null, null, null, getUrl(e.getPhotoUrl()), null, null, e.getPages(),
					null, e.getId(), e.getPublishing(), null, e.getIsbn(), e.getBookName(), null, null, null,
					null, e.getPrice(), SZMESOFT);
			// 上传图片
			try {
				tmp.setImage(uploadImage(e.getPhotoUrl()));
			} catch (Exception ex) {
				log.warn(BookConvertConstant.UPLOAD_IMAGE_FAILURE + tmp.getImage(), ex);
			}
			// 作者
			List<Author> authors = new ArrayList<>();
			authors.add(new Author(e.getAuthor()));
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
		ResponseEntity<byte[]> result = imageClient.getBookPhoto(path);
		return uploadService.uploadByte(result.getBody(), path);
	}

	private String getUrl(String photoUrl) {
		return URL + photoUrl;
	}

}
