package com.nb.module.partner.aliyun.oss.path.impl;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.nb.module.partner.aliyun.holder.AliyunHolder;
import com.nb.module.partner.aliyun.oss.path.IPathService;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;

@Service
public class PathServiceImpl extends CommonServiceImpl implements IPathService {

	@Autowired
	private AliyunHolder holder;

	@Override
	public String generatePresignedUrl(String filename) {
		OSS client = holder.getClient();
		// 生成返回url
		URL url = client.generatePresignedUrl(holder.getBucketName(), filename, getDate(), HttpMethod.GET);
		return url.toString();
	}

	private Date getDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, 2);
		return calendar.getTime();
	}

	@Override
	public String generateSaveUrl(String filename) {
		return generatePresignedUrl(filename).split("\\?")[0];
	}
}
