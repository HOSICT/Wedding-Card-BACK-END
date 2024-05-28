package com.example.weddingCard.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.example.weddingCard.entity.ImagesUrl;
import com.example.weddingCard.entity.Information;
import com.example.weddingCard.repository.ImagesUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private ImagesUrlRepository imagesUrlRepository;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;

    @Transactional
    public String uploadFile(MultipartFile multipartFile, String prefix) throws IOException {
        if (multipartFile != null) {
            File file = multiPartFileToFile(multipartFile);
            String fileName = prefix + "_" + System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file));
            String imagesS3Url = amazonS3.getUrl(bucketName, fileName).toString();
            file.delete();
            return imagesS3Url;
        }

        return null;
    }

    @Transactional
    public List<ImagesUrl> saveImagesUrl(String[] arrayMultipartFile, Information information) throws IOException {

        List<ImagesUrl> findWeddingIdImagesUrl = imagesUrlRepository.findByWeddingId(information);
        if (!findWeddingIdImagesUrl.isEmpty()) {
            for (ImagesUrl url : findWeddingIdImagesUrl) {
                imagesUrlRepository.delete(url);
                deleteFile(url);
            }
        }

        List<ImagesUrl> saveImagesUrlArray = new ArrayList<>();

        for (String imagesS3Url : arrayMultipartFile) {

            ImagesUrl imagesUrl = new ImagesUrl();

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

    private void deleteFile(ImagesUrl url) {
        String urlGetString = url.getUrl();
        String decodeUrl;
        try{
            decodeUrl = URLDecoder.decode(urlGetString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            decodeUrl = urlGetString;
            e.printStackTrace();
        }
        String fileUrl = decodeUrl.substring(decodeUrl.lastIndexOf("/") + 1);

        amazonS3.deleteObject(bucketName, fileUrl);
    }

    public List<ImagesUrl> findImagesUrlByWeddingId(List<Information> information) {
        return imagesUrlRepository.findByWeddingIdIn(information);
    }

    @Transactional
    public void deleteFilesFromS3(Information information) {
        List<ImagesUrl> imagesUrls = imagesUrlRepository.findByWeddingId(information);
        for (ImagesUrl imagesUrl : imagesUrls) {
            String url = imagesUrl.getUrl();
            String fileName = url.substring(url.lastIndexOf("/") + 1);
            amazonS3.deleteObject(new DeleteObjectRequest(bucketName, fileName));
            imagesUrlRepository.delete(imagesUrl);
        }
    }

}