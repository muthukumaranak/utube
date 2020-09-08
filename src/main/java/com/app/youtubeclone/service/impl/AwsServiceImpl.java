package com.app.youtubeclone.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.app.youtubeclone.entity.MediaFile;
import com.app.youtubeclone.entity.Users;
import com.app.youtubeclone.repository.MediaFileRepo;
import com.app.youtubeclone.repository.UsersRepo;
import com.app.youtubeclone.service.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AwsServiceImpl implements AwsService {

    @Autowired
    private MediaFileRepo mediaFileRepo;

    @Autowired
    private AmazonS3 s3client;

    @Autowired
    UsersRepo usersRepo;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    @Override
    public void upload(String title, String description, String tags, String restriction,
                       String visibility, MultipartFile thumbnail, MultipartFile video,String duration, Boolean saved) {
        try {
            String thumbnailOriginalFilename = thumbnail.getOriginalFilename();
            String videoOriginalFilename = video.getOriginalFilename();

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(thumbnail.getSize());
            s3client.putObject(bucketName, thumbnailOriginalFilename, thumbnail.getInputStream(), metadata);
            String thumbnailurl = s3client.getUrl(bucketName, thumbnailOriginalFilename).toString();

            metadata.setContentLength(video.getSize());
            s3client.putObject(bucketName, videoOriginalFilename, video.getInputStream(), metadata);
            String videourl = s3client.getUrl(bucketName, videoOriginalFilename).toString();

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String created_at = f.format(new Date());

            saved = false;
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Users users = usersRepo.findByEmail(auth.getName());
            MediaFile mediaFile = new MediaFile(title, description, tags, restriction, created_at, visibility, thumbnailurl, videourl,
                    users.getEmail(),duration, saved);
            mediaFileRepo.save(mediaFile);


        } catch (Exception e) {
            System.out.println(e);
        }


    }

    @Override
    public void delete(String keyName) {

    }
}