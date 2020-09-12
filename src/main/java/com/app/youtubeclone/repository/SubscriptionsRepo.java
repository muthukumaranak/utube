package com.app.youtubeclone.repository;

import com.app.youtubeclone.entity.Subscriptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SubscriptionsRepo extends JpaRepository<Subscriptions, Integer> {


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO subscriptions(channelid, username) values (?1,?2)",nativeQuery = true)
    void insert(int id,String name);


    @Transactional
    @Modifying
    @Query(value = "delete from subscriptions where channelid=?1 and username=?2",nativeQuery = true)
    void remove(int id, String name);

    @Query(value = "select channelid from subscriptions where username=?1",nativeQuery = true)
    List<Integer> findbyuser(String name);

    @Query(value = "select count(*) from subscriptions where channelid = ?1", nativeQuery = true)
    int findSubs(int channelId);
}
