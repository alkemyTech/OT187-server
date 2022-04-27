package com.alkemy.ong.service;

import com.alkemy.ong.dto.AmazonS3ResponseDto;
import com.alkemy.ong.exception.AmazonS3IOException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import static com.alkemy.ong.utility.Constantes.AWS_EXCEPTION_NOT_FOUND;
import static com.alkemy.ong.utility.Constantes.AWS_EXCEPTION_ERR_UPLOAD;

@Service
public class AmazonS3ServiceImp implements AmazonS3Service {

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    @Autowired
    private AmazonS3 amazonS3;

    private File convertMultiPartToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multipartFile) {
        return new Date().getTime() + "-" + multipartFile.getOriginalFilename().replaceAll(" ", "_");
    }

    private void uploadFileToAmazonS3Bucket(String fileName, File file) {
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    @Override
    public AmazonS3ResponseDto uploadFile(MultipartFile multipartFile) {

        String fileURL = "";

        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            fileURL = endpointUrl + fileName;

            uploadFileToAmazonS3Bucket(fileName, file);

            file.delete();
        } catch (FileNotFoundException e) {
            throw new AmazonS3IOException(AWS_EXCEPTION_NOT_FOUND);
        } catch (IOException e) {
            throw new AmazonS3IOException(AWS_EXCEPTION_ERR_UPLOAD);
        }

        return new AmazonS3ResponseDto(fileURL);
    }

    @Override
    public void deleteFileFromAmazonS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        amazonS3.deleteObject(new DeleteObjectRequest(bucketName + "/", fileName));
    }
}