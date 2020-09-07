package com.app.youtubeclone.repository;


import com.app.youtubeclone.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update users set channel_status = 'true' where email=?1",nativeQuery = true)
    void channelUpdate(String email);
    Users findByEmail(String email);
}
