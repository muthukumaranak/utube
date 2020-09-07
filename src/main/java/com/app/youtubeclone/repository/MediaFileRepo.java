package com.app.youtubeclone.repository;


import com.amazonaws.services.dynamodbv2.xspec.S;
import com.app.youtubeclone.entity.MediaFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MediaFileRepo extends JpaRepository<MediaFile, Integer> {

    @Query("SELECT m FROM MediaFile m  WHERE m.title LIKE %?1%" + "OR m.description LIKE %?1%" + "OR m.owner LIKE %?1%" + "OR m.tag LIKE %?1%")
    Page<MediaFile> search(String keyword, Pageable pageable);

    @Query("SELECT m FROM MediaFile m WHERE m.tag LIKE %?1%")
    Page<MediaFile> filterByTag(String tag, Pageable pageable);

    List<MediaFile> findTop2ByOrderByViewsDesc();


    //@Query("SELECT m FROM MediaFile m WHERE m.watchLater = :true")
    //List<MediaFile> findAllWatchLater();


    @Query(value = "select likes from media_file where id=?1", nativeQuery = true)
    int findLikes(int parseInt);

    @Transactional
    @Modifying
    @Query(value = "update media_file set likes = ?1 where id = ?2",nativeQuery = true)
    void updateLikes(int total_likes, int parseInt);

    @Query(value = "select dislikes from media_file where id=?1", nativeQuery = true)
    int findDisLikes(int parseInt);

    @Transactional
    @Modifying
    @Query(value = "update media_file set dislikes = ?1 where id = ?2",nativeQuery = true)
    void updateDisLikes(int i, int parseInt);

    @Query(value = "select views from media_file where id=?1", nativeQuery = true)
    int findViews(int id);

    @Transactional
    @Modifying
    @Query(value = "update media_file set views = ?1 where id = ?2",nativeQuery = true)
    void updateViews(int i, int id);

    @Query(value = "select * from media_file where owner=?1",nativeQuery = true)
    List<MediaFile> myvideos(String email);


}