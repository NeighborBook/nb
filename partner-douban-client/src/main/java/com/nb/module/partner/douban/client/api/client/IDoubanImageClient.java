package com.nb.module.partner.douban.client.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "doubanimage", url = "https://img3.doubanio.com")
@RequestMapping("/view/subject")
public interface IDoubanImageClient {

	@RequestMapping(value = "/m/public/{filename}", method = RequestMethod.GET)
	ResponseEntity<byte[]> mImage(@PathVariable(value = "filename") String filename);

}
