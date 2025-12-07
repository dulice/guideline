package com.example.guideline.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
public class S3Image {
    public static String upload(MultipartFile file) {
        log.info("Uploading file to S3");
        return file.getOriginalFilename();
    }

    public static void delete(String imageObj) {
        log.info("Deleting file from S3");
    }
}
