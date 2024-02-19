package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.model.await.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
    @Query(value = "select u from UserInfo u where u.user.id = :userId")
    UserInfo findUserInfoByUserId(Long userId);

    UserInfo findUserInfoByEmail(String email);

    boolean existsByUsernameIgnoreCase(String name);
}
