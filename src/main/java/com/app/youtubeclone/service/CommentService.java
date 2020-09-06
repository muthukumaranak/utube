package com.app.youtubeclone.service;

import com.amazonaws.services.kinesisanalytics.model.ApplicationUpdate;
import com.app.youtubeclone.entity.MediaComment;

public interface CommentService {

    public void saveComment(MediaComment comment);
    public void deleteCommentById(int commentId);
}