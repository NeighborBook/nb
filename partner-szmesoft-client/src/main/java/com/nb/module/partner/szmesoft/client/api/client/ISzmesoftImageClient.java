package com.nb.module.partner.szmesoft.client.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "szmesoftimage", url = "http://isbn.szmesoft.com")
@RequestMapping("/ISBN")
public interface ISzmesoftImageClient {

	@RequestMapping(value = "/GetBookPhoto", method = RequestMethod.GET)
	ResponseEntity<byte[]> getBookPhoto(@RequestParam(value = "id") String filename);

}
