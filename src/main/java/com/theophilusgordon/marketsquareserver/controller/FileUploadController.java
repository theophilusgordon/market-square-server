package com.theophilusgordon.marketsquareserver.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.theophilusgordon.marketsquareserver.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/files")
public class FileUploadController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private AmazonS3 amazonS3Client;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.uploadFile(file);
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest("market-sqaure-storage", fileName);
        generatePresignedUrlRequest.setExpiration(new Date(System.currentTimeMillis() + 3600000));
        URL fileUrl = amazonS3Client.generatePresignedUrl(generatePresignedUrlRequest);
        return new ResponseEntity<>("File uploaded successfully: " + fileUrl, HttpStatus.OK);
    }
}
