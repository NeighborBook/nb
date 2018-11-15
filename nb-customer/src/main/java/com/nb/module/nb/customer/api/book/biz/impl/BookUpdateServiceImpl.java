package com.nb.module.nb.customer.api.book.biz.impl;

import com.nb.module.nb.customer.api.book.biz.IBookService;
import com.nb.module.nb.customer.api.book.biz.IBookUpdateService;
import com.nb.module.nb.customer.api.book.domain.Book;
import com.nb.module.nb.customer.api.isbn.convert.constant.BookConvertConstant;
import com.nb.module.partner.aliyun.oss.biz.IUploadService;
import com.nb.module.partner.aliyun.oss.path.IPathService;
import com.zjk.module.common.base.adaptor.feign.impl.FeignAdaptorProvider;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Slf4j
@Service
public class BookUpdateServiceImpl extends CommonServiceImpl implements IBookUpdateService {

	@Autowired
	private IUploadService uploadService;
	@Autowired
	private IPathService pathService;
	@Autowired
	private IBookService bookService;
	@Autowired
	private FeignAdaptorProvider provider;

	@Override
	public String updateImage(String code, String url) {
		Book book = bookService.findOneByCode(code);
		try {
			book.setImage(uploadImage(url));
			bookService.update(book);
		} catch (Exception ex) {
			log.warn(BookConvertConstant.UPLOAD_IMAGE_FAILURE + url, ex);
		}
		return bookService.generatePresignedUrl(book.getImage());
	}

	@SneakyThrows
	private String uploadImage(String path) {
		ResponseEntity<byte[]> result = provider.getBytes(new URI(path));
		String filename = pathService.getFilename(path);
		return uploadService.uploadByte(result.getBody(), filename);
	}
}
