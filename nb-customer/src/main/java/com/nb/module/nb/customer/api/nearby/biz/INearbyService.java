package com.nb.module.nb.customer.api.nearby.biz;


import com.nb.module.nb.customer.api.nearby.domain.NearbyUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface INearbyService {

	Page<NearbyUser> findAllByLbsIdInAndUserCodeNot(List<String> lbsId, String userCode, Pageable pageable);

}
