package com.alkemy.ong.controller;

import com.alkemy.ong.dto.AmazonS3ResponseDto;
import com.alkemy.ong.service.AmazonS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static com.alkemy.ong.utility.Constantes.AWS_STORAGE_REQUEST;
import static com.alkemy.ong.utility.Constantes.AWS_DELETE_FILE;
import static com.alkemy.ong.utility.Constantes.AWS_UPLOAD_FILE;

@RestController
@RequestMapping(AWS_STORAGE_REQUEST)
public class AmazonS3Controller {
    @Autowired
    private AmazonS3Service amazonS3Service;

    @PostMapping(AWS_UPLOAD_FILE)
    public ResponseEntity<AmazonS3ResponseDto> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        return ResponseEntity.ok().body(amazonS3Service.uploadMultipartFile(multipartFile));
    }

    @DeleteMapping(AWS_DELETE_FILE)
    public ResponseEntity<Void> deleteFile(@RequestParam(value = "url") String url) {
        amazonS3Service.deleteFileFromAmazonS3Bucket(url);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
