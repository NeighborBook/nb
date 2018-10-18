package com.nb.module.partner.szmesoft.client.api.isbn.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "szmesoftisbn", url = "http://isbn.szmesoft.com")
@RequestMapping("/isbn")
public interface ISzmesoftISBNClient {

	@RequestMapping(value = "/query", method = RequestMethod.GET)
	String isbn(@RequestParam(value = "isbn") String isbn);

}
