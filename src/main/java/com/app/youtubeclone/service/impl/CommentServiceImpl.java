package com.app.youtubeclone.service.impl;

import com.app.youtubeclone.entity.MediaComment;
import com.app.youtubeclone.entity.MediaFile;
import com.app.youtubeclone.repository.MediaCommentRepo;
import com.app.youtubeclone.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private MediaCommentRepo mediaCommentRepo;

    @Override
    public void saveComment(MediaComment comment) {
        this.mediaCommentRepo.save(comment);
    }

    @Override
    public void deleteCommentById(int commentId) {
        Optional<MediaComment> mediaComment = mediaCommentRepo.findById(commentId);
        System.err.println(mediaComment.get());
        mediaCommentRepo.delete(mediaComment.get());
    }
}