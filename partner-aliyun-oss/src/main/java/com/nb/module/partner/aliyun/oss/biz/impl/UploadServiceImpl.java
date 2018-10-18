package com.nb.module.partner.aliyun.oss.biz.impl;

import com.aliyun.oss.OSS;
import com.nb.module.partner.aliyun.holder.AliyunHolder;
import com.nb.module.partner.aliyun.oss.biz.IUploadService;
import com.nb.module.partner.aliyun.oss.path.IPathService;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public class UploadServiceImpl extends CommonServiceImpl implements IUploadService {

	@Autowired
	private AliyunHolder holder;
	@Autowired
	private IPathService pathService;

	@Override
	public String uploadByte(byte[] content, String filename) {
		OSS client = holder.getClient();
		// 上传
		client.putObject(holder.getBucketName(), filename, new ByteArrayInputStream(content));
		return pathService.generateSaveUrl(filename);
	}
}
