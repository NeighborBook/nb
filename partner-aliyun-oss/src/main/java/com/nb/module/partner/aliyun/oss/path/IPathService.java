package com.nb.module.partner.aliyun.oss.path;


public interface IPathService {


	/**
	 * 生成访问路径
	 *
	 * @param filename
	 * @return
	 */
	String generatePresignedUrl(String filename);

	/**
	 * 路基 * 生成保存
	 *
	 * @param filename
	 * @return
	 */
	String generateSaveUrl(String filename);

	String getFilename(String path);
}
