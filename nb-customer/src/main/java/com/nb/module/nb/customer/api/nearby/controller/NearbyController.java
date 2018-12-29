package com.nb.module.nb.customer.api.nearby.controller;

import com.nb.module.nb.customer.api.nearby.biz.INearbyService;
import com.nb.module.nb.customer.api.nearby.domain.NearbyUser;
import com.zjk.module.common.base.annotation.CreateApiDocs;
import com.zjk.module.common.base.controller.BaseController;
import com.zjk.module.common.base.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/nearby")
public class NearbyController extends BaseController {

	@Autowired
	private INearbyService service;

	@ApiOperation(value = "通过地址，用户编号(!=)查询", notes = "通过地址，用户编号(!=)查询")
	@RequestMapping(value = "/findAllByLbsIdInAndUserCodeNot", method = RequestMethod.POST)
	public JsonContainer<Page<NearbyUser>> findAllByLbsIdInAndUserCodeNot(@RequestBody @NotNull List<String> lbsId, @RequestParam String userCode, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAllByLbsIdInAndUserCodeNot(lbsId, userCode, pageable));
	}

}
