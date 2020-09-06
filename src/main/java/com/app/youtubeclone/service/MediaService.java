package com.app.youtubeclone.service;

import com.app.youtubeclone.entity.MediaFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface MediaService {

    Page<MediaFile> findPage(int pageNo, int pageSize, String sortField, String sortDirection,String keyword,String tag);

    String createComment(String id, String comment);

    String like(String id);

    String dislike(String id);

    String views(int parseInt);
}