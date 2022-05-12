package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.dto.AmazonS3ResponseDto;
import com.alkemy.ong.service.AmazonS3Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.alkemy.ong.utility.Constantes.AWS_STORAGE_REQUEST;
import static com.alkemy.ong.utility.Constantes.AWS_DELETE_FILE;
import static com.alkemy.ong.utility.Constantes.AWS_UPLOAD_FILE;

@Tag(name = "Amazon S3")
@RestController
@RequestMapping(AWS_STORAGE_REQUEST)
public class AmazonS3Controller {

    @Autowired
    private AmazonS3Service amazonS3Service;

    @Operation(summary = "Upload a new file")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "File uploaded succesfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AmazonS3ResponseDto.class)) }),
    })
    @PostMapping(AWS_UPLOAD_FILE)
    public ResponseEntity<AmazonS3ResponseDto> uploadFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok().body(amazonS3Service.uploadFile(file));
    }

    @Operation(summary = "Delete a file")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "File deleted succesfully")
    })
    @DeleteMapping(AWS_DELETE_FILE)
    public ResponseEntity<Void> deleteFile(@RequestParam(value = "url") String url) {
        amazonS3Service.deleteFileFromAmazonS3Bucket(url);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
