package com.app.youtubeclone.repository;

import com.app.youtubeclone.entity.Library;
import com.app.youtubeclone.entity.MediaFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LibraryRepo extends JpaRepository<Library, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Library l WHERE videoId = ?1   AND userId = ?2 ")
    public void deleteLibrary(int videoId, int userId);

    @Query(value = "select * from media_file where id IN (select video_id from library where user_id = ?1)",nativeQuery = true)
    public List<Integer> findMedia(int userId);

    public List<MediaFile> findAllByUserId(int userId);
}
