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

    public static String moveFile(MinioClient client, String source, String target, String file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String filename = file.substring(file.lastIndexOf("/"));
        String url = AVATAR + filename;
        client.copyObject(
                CopyObjectArgs.builder()
                        .source(CopySource.builder()
                                .bucket(target)
                                .object(url).build())
                        .bucket(source)
                        .object(url)
                        .build()
        );
        deleteTemp(AVATAR, file, client);
        return WMS_URL + url;
    }

    public static String upload(String location, MultipartFile file, MinioClient client) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return WMS_URL + upload(location, WMS_BUCKET_NAME, file, client);
    }

    public static String uploadTemp(String location, MultipartFile file, MinioClient minioClient) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return TEMP_URL + upload(location, TEMP_BUCKET_NAME, file, minioClient);
    }

    public static void delete(String pre, String file, MinioClient client) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        del(WMS_URL, pre, file, client);
    }

    public static void deleteTemp(String pre, String file, MinioClient client) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        del(TEMP_URL, pre, file, client);
    }

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

    private static void del(String bucket, String pre, String fileName, MinioClient client) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String substring = fileName.substring(fileName.lastIndexOf("/"));
        RemoveObjectArgs args = RemoveObjectArgs.builder()
                .bucket(bucket)
                .object(pre + substring)
                .build();
        client.removeObject(args);
    }
}
