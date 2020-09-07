package com.app.youtubeclone.repository;

import com.app.youtubeclone.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelRepo extends JpaRepository<Channel, Long> {

    @Query(value = "select * from channels where owner=?1",nativeQuery = true)
    List<Channel> findByOwner(String email);
}
