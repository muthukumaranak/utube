package com.app.youtubeclone.controller;

import com.app.youtubeclone.entity.MediaComment;
import com.app.youtubeclone.entity.MediaFile;
import com.app.youtubeclone.repository.MediaCommentRepo;
import com.app.youtubeclone.repository.MediaFileRepo;
import com.app.youtubeclone.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private MediaFileRepo mediaFileRepo;

    @Autowired
    private MediaCommentRepo mediaCommentRepo;

    @PostMapping("/saveComment")
    public String saveComment(@ModelAttribute("mediaComment") MediaComment comment, Model model, @RequestParam("id")int mediaId,@ModelAttribute("mediaFiles") MediaFile mediaFiles){
        MediaFile mediaFile = mediaFileRepo.findById(mediaId).get();
        comment.setMediaFile(mediaFiles);
        comment.setCommentby(mediaFile.getOwner());
        comment.setCreated_at(new Date().toString());
        commentService.saveComment(comment);
        model.addAttribute("mediaComment", comment);
        model.addAttribute("mediaFiles", mediaFile);
        return "redirect:/";
    }

//    @GetMapping("/deleteComment")
//    public String deleteComment(@RequestParam(value = "id")Integer commentId,@ModelAttribute("mediaFiles") MediaFile mediaFiles,@ModelAttribute("mediaComment") MediaComment comment, Model model) {
//        System.out.println("comment id : " +commentId);
//          commentService.deleteCommentById(commentId);
//          model.addAttribute("mediaComment", comment);
//          model.addAttribute("mediaFiles", mediaFiles);
//          return "redirect:/";
//    }


    @GetMapping("/deleteComment/{commentId}/{mediaId}")
    public String deleteComment(@PathVariable(value = "commentId")int commentId,@PathVariable(value = "mediaId")int mediaId,Model model) {
        mediaCommentRepo.deleteById(commentId);
        MediaFile mediaFile = mediaFileRepo.findById(mediaId).get();
        model.addAttribute("mediaFiles",mediaFile);
        return "redirect:/";
    }




}