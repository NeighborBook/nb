package com.nb.module.nb.customer.api.isbn.convert.biz.impl;

import com.alibaba.fastjson.JSON;
import com.nb.module.nb.customer.api.book.biz.IBookService;
import com.nb.module.nb.customer.api.book.domain.Author;
import com.nb.module.nb.customer.api.book.domain.Book;
import com.nb.module.nb.customer.api.book.domain.Translator;
import com.nb.module.nb.customer.api.isbn.convert.biz.IBookConvertService;
import com.nb.module.nb.customer.api.isbn.convert.constant.BookConvertConstant;
import com.nb.module.nb.customer.api.isbn.convert.exception.BookConvertCode;
import com.nb.module.nb.customer.api.tag.domain.BookTag;
import com.nb.module.nb.customer.api.tag.domain.Tag;
import com.nb.module.partner.aliyun.oss.biz.IUploadService;
import com.nb.module.partner.douban.client.api.client.IDoubanImageClient;
import com.nb.module.partner.douban.client.api.isbn.client.IDoubanISBNClient;
import com.nb.module.partner.douban.client.api.isbn.domain.DoubanBook;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Order(1)
public class DoubanISBNServiceImpl extends CommonServiceImpl implements IBookConvertService {

	@Autowired
	private IBookService bookService;

	@Autowired
	private IDoubanISBNClient client;
	@Autowired
	private IDoubanImageClient imageClient;
	@Autowired
	private IUploadService uploadService;

	public static final String DOUBAN = "douban";

	@Override
	public Book findOneByISBN(String isbn) {
		DoubanBook book;
		try {
			String result = client.isbn(isbn);
			book = JSON.parseObject(result, DoubanBook.class);
		} catch (Exception e) {
			throw new BusinessException(BookConvertCode.BC0001, e, new Object[]{isbn});
		}
		return convert(book);
	}

	private Book convert(DoubanBook book) {
		return mapOneIfNotNull(book, e -> {
			// 书
			Book tmp = new Book(null, e.getSubtitle(), e.getPubdate(), e.getOriginTitle(), e.getImage(), e.getBinding(), e.getCatalog(), e.getPages(),
					e.getAlt(), e.getId(), e.getPublisher(), e.getIsbn10(), e.getIsbn13(), e.getTitle(), e.getUrl(), e.getAltTitle(), e.getAuthorIntro(),
					e.getSummary(), e.getPrice(), DOUBAN);
			// 上传图片
			try {
				tmp.setImage(uploadImage(e.getImage()));
			} catch (Exception ex) {
				log.warn(BookConvertConstant.UPLOAD_IMAGE_FAILURE + e.getImage(), ex);
			}
			// 作者
			tmp.setAuthors(map(e.getAuthor(), s -> new Author(s)));
			// 翻译
			tmp.setTranslators(map(e.getTranslator(), s -> new Translator(s)));
			// 标签
			tmp.setBookTags(map(e.getTags(), s -> new BookTag(s.getCount(), new Tag(null, s.getName(), s.getTitle()))));
			return tmp;
		});
	}

	@SneakyThrows
	private String uploadImage(String path) {
		String filename = bookService.getFilename(path);
		ResponseEntity<byte[]> result = imageClient.mImage(filename);
		return uploadService.uploadByte(result.getBody(), filename);
	}

}
