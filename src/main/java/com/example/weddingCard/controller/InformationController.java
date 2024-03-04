package com.example.weddingCard.controller;

import com.example.weddingCard.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/save")
public class InformationController {

    @Autowired
    private S3Service s3Service;

    @PostMapping("/information")
    public ResponseEntity<String> uploadFile(@RequestParam("images") MultipartFile[] files) {
        for (MultipartFile file : files) {
            try {
                s3Service.uploadFile(file);
            } catch (IOException e) {
                return ResponseEntity.internalServerError().body("File upload failed: " + e.getMessage());
            }
        }
        return ResponseEntity.ok("Files uploaded successfully");
    }
}