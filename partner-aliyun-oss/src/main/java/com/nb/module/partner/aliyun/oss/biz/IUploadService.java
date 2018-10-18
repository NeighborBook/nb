package com.nb.module.partner.aliyun.oss.biz;


public interface IUploadService {


	/**
	 * 上传Byte数组
	 *
	 * @param content
	 * @param filename
	 * @return
	 */
	String uploadByte(byte[] content, String filename);

}
