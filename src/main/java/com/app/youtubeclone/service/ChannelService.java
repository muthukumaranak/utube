package com.app.youtubeclone.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.app.youtubeclone.entity.Channel;
import com.app.youtubeclone.repository.ChannelRepo;
import com.app.youtubeclone.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ChannelService {

    @Autowired
    private AmazonS3 s3client;

    @Autowired
    ChannelRepo channelRepo;

    @Autowired
    UsersRepo usersRepo;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    public String register(String name, String description, MultipartFile thumbnail, String email) {
        try{
            String thumbnailOriginalFilename = thumbnail.getOriginalFilename();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(thumbnail.getSize());
            s3client.putObject(bucketName, thumbnailOriginalFilename, thumbnail.getInputStream(), metadata);
            String thumbnailurl = s3client.getUrl(bucketName, thumbnailOriginalFilename).toString();

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String created_at = f.format(new Date());
            
            Channel channel = new Channel(name, description, thumbnailurl, created_at, email);
            channelRepo.save(channel);

            usersRepo.channelUpdate(email);
            return "redirect:/";
        } catch (Exception e){
            System.out.println(e);
            return "redirect:/";
        }
    }
}