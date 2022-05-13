package com.alkemy.ong.service;

import com.alkemy.ong.dto.AmazonS3ResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface AmazonS3Service {

    AmazonS3ResponseDto uploadMultipartFile(MultipartFile multipartFile);
    AmazonS3ResponseDto uploadFile(File file);
    void deleteFileFromAmazonS3Bucket(String fileUrl);
}
