package com.nb.module.nb.customer.api.book.biz.impl;

import com.nb.module.nb.customer.api.book.biz.IBookService;
import com.nb.module.nb.customer.api.book.domain.Author;
import com.nb.module.nb.customer.api.book.domain.Book;
import com.nb.module.nb.customer.api.book.domain.BookMinInfo;
import com.nb.module.nb.customer.api.book.domain.Translator;
import com.nb.module.nb.customer.api.book.exception.BookCode;
import com.nb.module.nb.customer.api.tag.biz.ITagService;
import com.nb.module.nb.customer.api.userbook.biz.IUserBookService;
import com.nb.module.nb.customer.api.userbook.constant.UserBookConstant;
import com.nb.module.nb.customer.base.author.biz.ITNBAuthorService;
import com.nb.module.nb.customer.base.author.domain.TNBAuthor;
import com.nb.module.nb.customer.base.book.biz.ITNBBookService;
import com.nb.module.nb.customer.base.book.domain.TNBBook;
import com.nb.module.nb.customer.base.translator.biz.ITNBTranslatorService;
import com.nb.module.nb.customer.base.translator.domain.TNBTranslator;
import com.nb.module.partner.aliyun.oss.path.IPathService;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl extends CommonServiceImpl implements IBookService {

	@Autowired
	private ITNBBookService bookService;
	@Autowired
	private ITNBAuthorService authorService;
	@Autowired
	private ITNBTranslatorService translatorService;

	@Autowired
	private ITagService tagService;
	@Autowired
	private IUserBookService userBookService;
	@Autowired
	private IPathService pathService;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Book findOneByISBN(String isbn) {
		return mapOneIfNotNull(bookService.findOneByISBN(isbn), e -> convert(e));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Book findOneByCode(String code) {
		return mapOneIfNotNull(bookService.findOneByCode(code), e -> convert(e));
	}

	@Override
	public Book convert(TNBBook e) {
		// 书
		Book book = new Book(e.getCode(), e.getSubtitle(), e.getPubdate(), e.getOriginTitle(), generatePresignedUrl(e.getImage()), e.getBinding(), e.getCatalog(),
				e.getPages(), e.getAlt(), e.getDbId(), e.getPublisher(), e.getIsbn10(), e.getIsbn13(), e.getTitle(), e.getUrl(), e.getAltTitle(),
				e.getAuthorIntro(), e.getSummary(), e.getPrice(), e.getSource());
		// 作者
		book.setAuthors(map(authorService.findAllByCode(e.getCode()), s -> new Author(s.getAuthor())));
		// 翻译
		book.setTranslators(map(translatorService.findAllByCode(e.getCode()), s -> new Translator(s.getTranslator())));
		// 标签
		book.setBookTags(tagService.findAllByBookCodeOrderByTagCountDesc(e.getCode()));
		// 用户书（查拥有该书的用户，仅能查共享的）
		book.setUserBooks(userBookService.findAllByBookCodeAndSharable(book.getCode(), UserBookConstant.SHARABLE, PageRequest.of(0, 3)));
		return book;
	}

	@Override
	@Transactional
	public void save(Book vo) {
		// 书
		TNBBook bookPO = bookService.newInstance();
		bookPO.setSubtitle(vo.getSubtitle());
		bookPO.setPubdate(vo.getPubdate());
		bookPO.setOriginTitle(vo.getOriginTitle());
		bookPO.setImage(vo.getImage());
		bookPO.setBinding(vo.getBinding());
		bookPO.setCatalog(vo.getCatalog());
		bookPO.setPages(vo.getPages());
		bookPO.setAlt(vo.getAlt());
		bookPO.setDbId(vo.getDbId());
		bookPO.setPublisher(vo.getPublisher());
		bookPO.setIsbn10(vo.getIsbn10());
		bookPO.setIsbn13(vo.getIsbn13());
		bookPO.setTitle(vo.getTitle());
		bookPO.setUrl(vo.getUrl());
		bookPO.setAltTitle(vo.getAltTitle());
		bookPO.setAuthorIntro(vo.getAuthorIntro());
		bookPO.setSummary(vo.getSummary());
		bookPO.setPrice(vo.getPrice());
		bookPO.setSource(vo.getSource());
		bookService.save(bookPO);
		// 书code
		vo.setCode(bookPO.getCode());

		// 作者
		vo.getAuthors().forEach(e -> {
			TNBAuthor authorPO = new TNBAuthor();
			authorPO.setCode(bookPO.getCode());
			authorPO.setAuthor(e.getAuthor());
			authorService.save(authorPO);
		});

		// 翻译
		vo.getTranslators().forEach(e -> {
			TNBTranslator translatorPO = new TNBTranslator();
			translatorPO.setCode(bookPO.getCode());
			translatorPO.setTranslator(e.getTranslator());
			translatorService.save(translatorPO);
		});

		// 标签
		tagService.saveBookTags(bookPO.getCode(), vo.getBookTags());
	}

	@Override
	@Transactional
	public void update(Book vo) {
		// 书
		TNBBook bookPO = bookService.findOneByCode(vo.getCode());
		if (null != bookPO) {
			bookPO.setImage(vo.getImage());

			bookService.save(bookPO);
		}
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<BookMinInfo> findAll(Pageable pageable) {
		return bookService.findAll(pageable).map(e -> new BookMinInfo(convert(e)));
	}

	@Override
	public String generatePresignedUrl(String path) {
		return pathService.generatePresignedUrl(pathService.getFilename(path));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<BookMinInfo> findAllByTagCode(List<String> tagCodes, Integer sharable, Pageable pageable) {
		return bookService.findAllByTagCode(tagCodes, tagCodes.size(), sharable, pageable).map(e -> new BookMinInfo(convert(e)));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<BookMinInfo> findAllByTagCodeAndUserCodeNot(List<String> tagCodes, Integer sharable, String userCode, Pageable pageable) {
		return bookService.findAllByTagCodeAndUserCodeNot(tagCodes, null == tagCodes ? 0 : tagCodes.size(), sharable, userCode, pageable).map(e -> new BookMinInfo(convert(e)));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<BookMinInfo> findAllByTagCodeAndUserCode(List<String> tagCodes, Integer sharable, String userCode, Pageable pageable) {
		return bookService.findAllByTagCodeAndUserCode(tagCodes, null == tagCodes ? 0 : tagCodes.size(), sharable, userCode, pageable).map(e -> new BookMinInfo(convert(e)));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<BookMinInfo> findAllBySearch(String search, Pageable pageable) {
		return bookService.findAllBySearch(search, pageable).map(e -> new BookMinInfo(convert(e)));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<BookMinInfo> findAllBySearchAndUserCodeNot(String search, String userCode, Pageable pageable) {
		return bookService.findAllBySearchAndUserCodeNot(search, userCode, pageable).map(e -> new BookMinInfo(convert(e)));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<BookMinInfo> findAllBySearchAndUserCode(String search, String userCode, Pageable pageable) {
		return bookService.findAllBySearchAndUserCode(search, userCode, pageable).map(e -> new BookMinInfo(convert(e)));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<BookMinInfo> findAllByLbsIdAndUserCodeNot(List<String> lbsId, Integer sharable, String userCode, Pageable pageable) {
		if (null == lbsId || lbsId.isEmpty()) {
			throw new BusinessException(BookCode.B0001, new Object[]{userCode});
		}
		return bookService.findAllByLbsIdAndUserCodeNot(lbsId, sharable, userCode, pageable).map(e -> new BookMinInfo(convert(e)));
	}
}
