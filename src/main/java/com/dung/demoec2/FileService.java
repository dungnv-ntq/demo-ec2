package com.dung.demoec2;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;

@Service
public class FileService {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${s3.bucket.name}")
    private String s3BucketName;

    @Async
    public void uploadFile(final MultipartFile multipartFile) throws IOException {
        final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String fileName = LocalDateTime.now() + "_" + file.getName();
        final PutObjectRequest objectRequest = new PutObjectRequest(s3BucketName, fileName, file);
        amazonS3.putObject(objectRequest);
        Files.delete(file.toPath()); // delete local file
    }
}
