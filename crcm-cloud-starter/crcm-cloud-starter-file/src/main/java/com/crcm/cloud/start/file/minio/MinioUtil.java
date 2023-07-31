package com.crcm.cloud.start.file.minio;

import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName MinIoUtils
 * @Description MinIo文件工具类
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author Administrator
 * @Date 2020/9/28/11:45
 **/
@Data
@Builder
public class MinioUtil {

    private String url;
    private String accessKey;
    private String secretKey;
    private String bucketName;
    private Integer port;
    /**
     * 分片存储桶
     */
    private String chunkBucket;

    private MinioClient minioClient;

    /**
     * 排序
     */
    public final static boolean SORT = true;
    /**
     * 不排序
     */
    public final static boolean NOT_SORT = false;
    /**
     * 默认过期时间(分钟)
     */
    private final static Integer DEFAULT_EXPIRY = 60;
    /**
     * 默认文件上传切片大小
     */
    private final static Long DEFAULT_PART_SIZE = 5L * 1024 * 1024;


    /**
     * 初始化MinIo对象
     * 调用前请手动初始化
     */
    public static MinioUtil init(MinioConfig minioConfig) {
        MinioClient client = null;
        if (minioConfig.getPort() != null) {
            client = io.minio.MinioClient.builder()
                    .endpoint(minioConfig.getUrl(), minioConfig.getPort(), true)
                    .credentials(minioConfig.getAccessKey(), minioConfig.getSecretKey()).build();
        } else {
            client = io.minio.MinioClient.builder()
                    .endpoint(minioConfig.getUrl())
                    .credentials(minioConfig.getAccessKey(), minioConfig.getSecretKey()).build();
        }
        return MinioUtil.builder()
                .url(minioConfig.getUrl())
                .accessKey(minioConfig.getAccessKey())
                .secretKey(minioConfig.getSecretKey())
                .bucketName(minioConfig.getBucketName())
                .port(minioConfig.getPort())
                .minioClient(client).build();
    }

    /**
     * 存储桶是否存在
     *
     * @param bucketName 存储桶名称
     * @return true/false
     */
    @SneakyThrows
    public boolean isBucketExist(String bucketName) {
        return this.minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }


    /**
     * 创建存储桶
     *
     * @param bucketName 存储桶名称
     * @return true/false
     */
    @SneakyThrows
    public boolean createBucket(String bucketName) {
        this.minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        return true;
    }

    /**
     * 上传文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param stream     文件流
     * @param size       大小
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    public String putObject(String bucketName, String objectName, InputStream stream, long size) throws Exception {
        ObjectWriteResponse response = this.minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .stream(stream, size, DEFAULT_PART_SIZE)
                .build());
        return response.object();
    }

    /**
     * 上传文件
     *
     * @param bucketName  bucket名称
     * @param objectName  文件名称
     * @param stream      文件流
     * @param size        大小
     * @param contextType 类型
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    public String putObject(String bucketName, String objectName, InputStream stream, long size, String contextType) throws Exception {
        ObjectWriteResponse response = this.minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .stream(stream, size, DEFAULT_PART_SIZE)
                .contentType(contextType)
                .build());
        return response.object();
    }


    /**
     * 获取访问对象的外链地址
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @param expiry     过期时间(分钟) 最大为7天 超过7天则默认最大值
     * @return viewUrl
     */
    @SneakyThrows
    public String getObjectUrl(String bucketName, String objectName, Integer expiry) {
        expiry = expiryHandle(expiry);
        return this.minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(objectName)
                        .expiry(expiry)
                        .build()
        );
    }

    /**
     * 获取对象输入流
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @return
     * @throws Exception
     */
    public InputStream getObject(String bucketName, String objectName) throws Exception {
        return this.minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .build()
        );
    }

    /**
     * 创建上传文件对象的外链
     *
     * @param bucketName 存储桶名称
     * @param objectName 欲上传文件对象的名称
     * @param expiry     过期时间(分钟) 最大为7天 超过7天则默认最大值
     * @return uploadUrl
     */
    @SneakyThrows
    public String createUploadUrl(String bucketName, String objectName, Integer expiry) {
        expiry = expiryHandle(expiry);
        return this.minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.PUT)
                        .bucket(bucketName)
                        .object(objectName)
                        .expiry(expiry)
                        .build()
        );
    }

    /**
     * 创建上传文件对象的外链
     *
     * @param bucketName 存储桶名称
     * @param objectName 欲上传文件对象的名称
     * @return uploadUrl
     */
    public String createUploadUrl(String bucketName, String objectName) {
        return createUploadUrl(bucketName, objectName, DEFAULT_EXPIRY);
    }

    /**
     * 批量创建分片上传外链
     *
     * @param bucketName 存储桶名称
     * @param objectMD5  欲上传分片文件主文件的MD5
     * @param chunkCount 分片数量
     * @return uploadChunkUrls
     */
    public List<String> createUploadChunkUrlList(String bucketName, String objectMD5, Integer chunkCount) {
        if (null == bucketName) {
            bucketName = this.chunkBucket;
        }
        if (null == objectMD5) {
            return null;
        }
        objectMD5 += "/";
        if (null == chunkCount || 0 == chunkCount) {
            return null;
        }
        List<String> urlList = new ArrayList<>(chunkCount);
        for (int i = 1; i <= chunkCount; i++) {
            String objectName = objectMD5 + i + ".chunk";
            urlList.add(createUploadUrl(bucketName, objectName, DEFAULT_EXPIRY));
        }
        return urlList;
    }

    /**
     * 创建指定序号的分片文件上传外链
     *
     * @param bucketName 存储桶名称
     * @param objectMD5  欲上传分片文件主文件的MD5
     * @param partNumber 分片序号
     * @return uploadChunkUrl
     */
    public String createUploadChunkUrl(String bucketName, String objectMD5, Integer partNumber) {
        if (null == bucketName) {
            bucketName = this.chunkBucket;
        }
        if (null == objectMD5) {
            return null;
        }
        objectMD5 += "/" + String.format("%06d", partNumber) + ".chunk";
        return createUploadUrl(bucketName, objectMD5, DEFAULT_EXPIRY);
    }

    /**
     * 获取对象文件名称列表
     *
     * @param bucketName 存储桶名称
     * @param prefix     对象名称前缀
     * @param sort       是否排序(升序)
     * @return objectNames
     */
    @SneakyThrows
    public List<String> listObjectNames(String bucketName, String prefix, Boolean sort) {
        ListObjectsArgs listObjectsArgs;
        if (null == prefix) {
            listObjectsArgs = ListObjectsArgs.builder()
                    .bucket(bucketName)
                    .recursive(true)
                    .build();
        } else {
            listObjectsArgs = ListObjectsArgs.builder()
                    .bucket(bucketName)
                    .prefix(prefix)
                    .recursive(true)
                    .build();
        }
        Iterable<Result<Item>> chunks = this.minioClient.listObjects(listObjectsArgs);
        List<String> chunkPaths = new ArrayList<>();
        for (Result<Item> item : chunks) {
            chunkPaths.add(item.get().objectName());
        }
        if (sort) {
            return chunkPaths.stream().distinct().collect(Collectors.toList());
        }
        return chunkPaths;
    }

    /**
     * 获取对象文件名称列表
     *
     * @param bucketName 存储桶名称
     * @param prefix     对象名称前缀
     * @return objectNames
     */
    public List<String> listObjectNames(String bucketName, String prefix) {
        return listObjectNames(bucketName, prefix, NOT_SORT);
    }

    /**
     * 获取分片文件名称列表
     *
     * @param bucketName 存储桶名称
     * @param ObjectMd5  对象Md5
     * @return objectChunkNames
     */
    public List<String> listChunkObjectNames(String bucketName, String ObjectMd5) {
        if (null == bucketName) {
            bucketName = this.chunkBucket;
        }
        if (null == ObjectMd5) {
            return null;
        }
        return listObjectNames(bucketName, ObjectMd5, SORT);
    }

    /**
     * 获取分片名称地址HashMap key=分片序号 value=分片文件地址
     *
     * @param bucketName 存储桶名称
     * @param ObjectMd5  对象Md5
     * @return objectChunkNameMap
     */
    public Map<Integer, String> mapChunkObjectNames(String bucketName, String ObjectMd5) {
        if (null == bucketName) {
            bucketName = this.chunkBucket;
        }
        if (null == ObjectMd5) {
            return null;
        }
        List<String> chunkPaths = listObjectNames(bucketName, ObjectMd5);
        if (null == chunkPaths || chunkPaths.size() == 0) {
            return null;
        }
        Map<Integer, String> chunkMap = new HashMap<>(chunkPaths.size());
        for (String chunkName : chunkPaths) {
            Integer partNumber = Integer.parseInt(chunkName.substring(chunkName.indexOf("/") + 1, chunkName.lastIndexOf(".")));
            chunkMap.put(partNumber, chunkName);
        }
        return chunkMap;
    }

    /**
     * 合并分片文件成对象文件
     *
     * @param chunkBucKetName   分片文件所在存储桶名称
     * @param composeBucketName 合并后的对象文件存储的存储桶名称
     * @param chunkNames        分片文件名称集合
     * @param objectName        合并后的对象文件名称
     * @return true/false
     */
    @SneakyThrows
    public boolean composeObject(String chunkBucKetName, String composeBucketName, List<String> chunkNames, String objectName) {
        if (null == chunkBucKetName) {
            chunkBucKetName = this.chunkBucket;
        }
        List<ComposeSource> sourceObjectList = new ArrayList<>(chunkNames.size());
        for (String chunk : chunkNames) {
            sourceObjectList.add(
                    ComposeSource.builder()
                            .bucket(chunkBucKetName)
                            .object(chunk)
                            .build()
            );
        }
        this.minioClient.composeObject(
                ComposeObjectArgs.builder()
                        .bucket(composeBucketName)
                        .object(objectName)
                        .sources(sourceObjectList)
                        .build()
        );
        return true;
    }

    /**
     * 合并分片文件成对象文件
     *
     * @param bucketName 存储桶名称
     * @param chunkNames 分片文件名称集合
     * @param objectName 合并后的对象文件名称
     * @return true/false
     */
    public boolean composeObject(String bucketName, List<String> chunkNames, String objectName) {
        return composeObject(this.chunkBucket, bucketName, chunkNames, objectName);
    }

    /**
     * 批量删除对象
     *
     * @param bucKetName  存储桶名称
     * @param objectNames 需要删除的对象名称集合
     * @return
     */
    public boolean removeObjects(String bucKetName, List<String> objectNames) {
        List<DeleteObject> deleteObjects = new ArrayList<>(objectNames.size());
        for (String name : objectNames) {
            deleteObjects.add(new DeleteObject(name));
        }
        Iterable<Result<DeleteError>> results = this.minioClient.removeObjects(RemoveObjectsArgs.builder()
                .bucket(bucKetName)
                .objects(deleteObjects)
                .build());
        for (Result<DeleteError> result : results) {
            DeleteError error = null;
            try {
                error = result.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(
                    "Error in deleting object " + error.objectName() + "; " + error.message());
        }
        return true;
    }

    /**
     * 将分钟数转换为秒数
     *
     * @param expiry 过期时间(分钟数)
     * @return expiry
     */
    private static int expiryHandle(Integer expiry) {
        expiry = expiry * 60;
        if (expiry > 604800) {
            return 604800;
        }
        return expiry;
    }
}
