package com.alkemy.ong.service;

import com.alkemy.ong.dto.AmazonS3ResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface AmazonS3Service {

    AmazonS3ResponseDto uploadFile(MultipartFile multipartFile);
    void deleteFileFromAmazonS3Bucket(String fileUrl);
}
