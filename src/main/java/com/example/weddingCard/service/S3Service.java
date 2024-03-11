package com.example.weddingCard.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.weddingCard.entity.ImagesUrl;
import com.example.weddingCard.entity.Information;
import com.example.weddingCard.repository.ImagesUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private ImagesUrlRepository imagesUrlRepository;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;

    public String uploadFile(MultipartFile multipartFile) throws IOException {
        File file = multiPartFileToFile(multipartFile);
        String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file));
        String imagesS3Url = amazonS3.getUrl(bucketName, fileName).toString();
        file.delete();

        return imagesS3Url;
    }

    public List<ImagesUrl> saveImagesUrl(MultipartFile[] arrayMultipartFile, Information information) throws IOException {
        List<ImagesUrl> saveImagesUrlArray = new ArrayList<>();

        List<ImagesUrl> findWeddingIdImagesUrl = imagesUrlRepository.findByWeddingId(information);
        if (!findWeddingIdImagesUrl.isEmpty()) {
            for (ImagesUrl url : findWeddingIdImagesUrl) {
                imagesUrlRepository.delete(url);
            }
        }
        for (MultipartFile multipartFile : arrayMultipartFile) {

            ImagesUrl imagesUrl = new ImagesUrl();

            String imagesS3Url = uploadFile(multipartFile);

            imagesUrl.setWeddingId(information);
            imagesUrl.setUrl(imagesS3Url);
            imagesUrlRepository.save(imagesUrl);

            saveImagesUrlArray.add(imagesUrl);
        }
        return saveImagesUrlArray;
    }

    private File multiPartFileToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fileOutputStream = new FileOutputStream(convertedFile)) {
            fileOutputStream.write(file.getBytes());
        }
        return convertedFile;
    }
}