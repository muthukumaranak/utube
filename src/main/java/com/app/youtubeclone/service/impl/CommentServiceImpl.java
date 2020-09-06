package com.app.youtubeclone.service.impl;

import com.app.youtubeclone.entity.MediaComment;
import com.app.youtubeclone.repository.MediaCommentRepo;
import com.app.youtubeclone.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        mediaCommentRepo.deleteById(commentId);
    }
}