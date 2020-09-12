package com.app.youtubeclone.repository;

import com.app.youtubeclone.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ChannelRepo extends JpaRepository<Channel, Long> {

    @Query(value = "select * from channels where owner=?1",nativeQuery = true)
    List<Channel> findByOwner(String email);

    @Transactional
    @Modifying
    @Query(value = "update channels set channel=?1, description = ?2 where owner=?3",nativeQuery = true)
    void update(String channel, String description, String owner);

    @Query(value = "select * from channels where id=?1",nativeQuery = true)
    List<Channel> findById(int id);

    @Query(value = "select owner from channels where id=?1",nativeQuery = true)
    String findByOwner(int id);

    @Query(value = "SELECT channel,cover_url,created_at,description,owner,subscribers,videos\n" +
            "FROM channels\n" +
            "inner JOIN subscriptions\n" +
            "ON channels.id == subscriptions.channelid where subscriptions.username=?1",nativeQuery = true)
    List<Channel> findSubscribed(String name);

    @Query(value = "SELECT channel,cover_url,created_at,description,owner,subscribers,videos\n" +
            "FROM channels\n" +
            "inner JOIN subscriptions\n" +
            "ON channels.id != subscriptions.channelid where subscriptions.username=?1",nativeQuery = true)
    List<Channel> findUnSubscribed(String name);

    @Query(value = "select id from channels where owner = ?1", nativeQuery = true)
    int findChannelIdByOwner(String name);
}
