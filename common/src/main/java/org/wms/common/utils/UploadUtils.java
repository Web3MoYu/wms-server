package org.wms.common.utils;

import io.minio.*;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.UUID;

public class UploadUtils {
    private static final String WMS_BUCKET_NAME = "wms";

    private static final String TEMP_BUCKET_NAME = "temp";

    private static final String WMS_URL = "http://localhost:9000/wms";

    private static final String TEMP_URL = "http://localhost:9000/temp";

    public static final String AVATAR = "/avatar";

    /**
     * 移动文件
     *
     * @param client minio客户端
     * @param source 源路径
     * @param target 目标路径
     * @param file   文件
     * @return 文件路径
     */
    public static String moveFile(MinioClient client, String source, String target, String file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String filename = file.substring(file.lastIndexOf("/"));
        String url = AVATAR + filename;
        client.copyObject(
                CopyObjectArgs.builder()
                        .source(CopySource.builder()
                                .bucket(source)
                                .object(url).build())
                        .bucket(target)
                        .object(url)
                        .build()
        );
        deleteTemp(AVATAR, file, client);
        return WMS_URL + url;
    }

    /**
     * 上传文件
     *
     * @param location 位置
     * @param file     文件
     * @param client   minio客户端
     * @return 文件路径
     */
    public static String upload(String location, MultipartFile file, MinioClient client) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return WMS_URL + upload(location, WMS_BUCKET_NAME, file, client);
    }

    /**
     * 上传临时文件
     *
     * @param location 位置
     * @param file     文件
     * @param client   minio客户端
     * @return 文件路径
     */
    public static String uploadTemp(String location, MultipartFile file, MinioClient minioClient) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return TEMP_URL + upload(location, TEMP_BUCKET_NAME, file, minioClient);
    }

    /**
     * 删除文件
     *
     * @param pre    前缀
     * @param file   文件
     * @param client minio客户端
     */
    public static void delete(String pre, String file, MinioClient client) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        del(WMS_BUCKET_NAME, pre, file, client);
    }

    /**
     * 删除临时文件
     *
     * @param pre    前缀
     * @param file   文件
     * @param client minio客户端
     */
    public static void deleteTemp(String pre, String file, MinioClient client) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        del(TEMP_BUCKET_NAME, pre, file, client);
    }

    /**
     * 上传文件
     *
     * @param pre    前缀
     * @param bucket 桶
     * @param file   文件
     * @param client minio客户端
     * @return 文件路径
     */
    private static String upload(String pre, String bucket, MultipartFile file, MinioClient client) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String suf = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
        String filename = pre + "/" + UUID.randomUUID()
                + suf;
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(bucket)
                .object(filename)
                .contentType(file.getContentType())
                .stream(file.getInputStream(), file.getSize(), -1)
                .build();
        client.putObject(args);
        return filename;
    }

    /**
     * 删除文件
     *
     * @param bucket   桶
     * @param pre      前缀
     * @param fileName 文件名
     * @param client   minio客户端
     */
    private static void del(String bucket, String pre, String fileName, MinioClient client) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String substring = fileName.substring(fileName.lastIndexOf("/"));
        RemoveObjectArgs args = RemoveObjectArgs.builder()
                .bucket(bucket)
                .object(pre + substring)
                .build();
        client.removeObject(args);
    }
}
