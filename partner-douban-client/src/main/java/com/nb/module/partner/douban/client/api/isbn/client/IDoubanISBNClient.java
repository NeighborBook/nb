package com.nb.module.partner.douban.client.api.isbn.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "doubanisbn", url = "https://api.douban.com/v2/book")
@RequestMapping("/isbn")
public interface IDoubanISBNClient {

	@RequestMapping(value = "/{isbn}", method = RequestMethod.GET)
	String isbn(@PathVariable(value = "isbn") String isbn);

}
