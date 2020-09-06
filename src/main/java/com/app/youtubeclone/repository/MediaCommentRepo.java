package com.app.youtubeclone.repository;

import com.app.youtubeclone.entity.MediaComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaCommentRepo extends JpaRepository<MediaComment, Integer> {
}
