package com.app.youtubeclone.repository;

import com.app.youtubeclone.entity.Library;
import com.app.youtubeclone.entity.MediaFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepo extends JpaRepository<Library, Integer> {


    public List<MediaFile> findAllByUserId(int userId);
}
