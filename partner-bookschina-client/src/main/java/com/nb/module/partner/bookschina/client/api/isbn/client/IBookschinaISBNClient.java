package com.nb.module.partner.bookschina.client.api.isbn.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "bookschinaisbn", url = "http://www.bookschina.com")
@RequestMapping("/ashx")
public interface IBookschinaISBNClient {

	@RequestMapping(value = "/AdvancedSearch.ashx", method = RequestMethod.GET)
	String isbn(@RequestParam(value = "isbn") String isbn);

}
