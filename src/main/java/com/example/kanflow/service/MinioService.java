package com.example.kanflow.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class MinioService {

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucketName;

    @Value("${minio.url}")
    private String minioUrl;

    // Uploads a file to the specified MinIO bucket and returns the URL.
    public String uploadFile(MultipartFile file) throws Exception {
        // Encode the objectName to handle spaces and special characters.
        String objectName = URLEncoder.encode(file.getOriginalFilename(), StandardCharsets.UTF_8.toString());

        // Upload the file to MinIO
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build());

        // Construct the accessible URL for the uploaded file.
        return minioUrl + "/" + bucketName + "/" + objectName;
    }
}
