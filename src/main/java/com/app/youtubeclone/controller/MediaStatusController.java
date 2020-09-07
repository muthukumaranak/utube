package com.app.youtubeclone.controller;


import com.app.youtubeclone.entity.MediaComment;
import com.app.youtubeclone.entity.MediaFile;
import com.app.youtubeclone.repository.MediaFileRepo;
import com.app.youtubeclone.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;


@Controller
public class MediaStatusController {

    @Autowired
    MediaService mediaService;

    @Autowired
    MediaFileRepo mediaFileRepo;

    @PostMapping("/comment")
    public String comment(@RequestParam String id, @RequestParam String comment){
        return mediaService.createComment(id,comment);
    }

    @PostMapping("/like")
    public String like(@RequestParam String id){
        return mediaService.like(id);
    }

    @PostMapping("/dislike")
    public String dislike(@RequestParam String id){
        return mediaService.dislike(id);
    }

    @PostMapping("/views")
    public String views(@RequestParam String id){
        return mediaService.views(Integer.parseInt(id));
    }

    @GetMapping("/receiveall")
    public String retrieve(Model model) {
        List<MediaFile> mediaFiles = mediaFileRepo.findAll();
        List<MediaFile> list = new LinkedList<>();
        mediaFiles.forEach(video -> {
            MediaFile mediaFileDTO = new MediaFile();
            mediaFileDTO.setTitle(video.getTitle());
            mediaFileDTO.setDescription(video.getDescription());
            mediaFileDTO.setId(video.getId());
            mediaFileDTO.setOwner(video.getOwner());
            mediaFileDTO.setThumbnailUrl(video.getThumbnailUrl());
            mediaFileDTO.setVideoUrl(video.getVideoUrl());
            mediaFileDTO.setTag(video.getTag());
            mediaFileDTO.setRestriction(video.getRestriction());
            mediaFileDTO.setCreatedAt(video.getCreatedAt());
            mediaFileDTO.setVisibility(video.getVisibility());
            mediaFileDTO.setLikes(video.getLikes());
            mediaFileDTO.setDislikes(video.getDislikes());
            mediaFileDTO.setViews(video.getViews());
            mediaFileDTO.setDuration(video.getDuration());
            List<MediaComment> commentDTOS = new LinkedList();
            video.getMediaComment().forEach(comment -> {
                MediaComment commentDTO = new MediaComment();
                commentDTO.setCommentby(comment.getCommentby());
                commentDTO.setComment(comment.getComment());
                commentDTO.setId(comment.getId());
                commentDTO.setMediaFile(comment.getMediaFile());
                commentDTO.setCreated_at(comment.getCreated_at());
                commentDTOS.add(commentDTO);
            });
            mediaFileDTO.setMediaComment(commentDTOS);
            list.add(mediaFileDTO);
        });
        model.addAttribute("list",list);
        return "home";
    }
}