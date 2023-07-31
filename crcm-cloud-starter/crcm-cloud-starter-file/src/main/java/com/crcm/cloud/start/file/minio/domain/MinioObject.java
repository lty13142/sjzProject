
package com.crcm.cloud.start.file.minio.domain;

import io.minio.ObjectStat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;


/**
 * 存储对象的元数据
 *
 */
@Data
@AllArgsConstructor
public class MinioObject {
	private String bucketName;
	private String name;
	private ZonedDateTime createdTime;
	private Long length;
	private String etag;
	private String contentType;

	public MinioObject(ObjectStat os) {
		this.bucketName = os.bucketName();
		this.name = os.name();
		this.createdTime = os.createdTime();
		this.length = os.length();
		this.etag = os.etag();
		this.contentType = os.contentType();
	}

}
