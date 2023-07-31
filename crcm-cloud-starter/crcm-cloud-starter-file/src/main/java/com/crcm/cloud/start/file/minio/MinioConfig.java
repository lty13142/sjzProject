
package com.crcm.cloud.start.file.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * minio 配置信息
 *
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "file.minio")
public class MinioConfig {
	/**
	 * minio 服务地址 http://ip
	 */
	private String url;
	/**
	 * 端口
	 */
	private Integer port;

	/**
	 * 用户名
	 */
	private String accessKey;

	/**
	 * 密码
	 */
	private String secretKey;
	/**
	 * 根目录名称
	 */
	private String bucketName;
	/**
	 * 存储分块上传文件的桶
	 */
	private String chunkBucKet;
	/**
	 * 分享链接过期时间(秒)
	 */
	private Integer fileExpireSecond;

}
