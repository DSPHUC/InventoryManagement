package com.example.inventorymanagement.service.user;

import com.example.inventorymanagement.model.await.UserInfo;
import com.example.inventorymanagement.service.IGeneralService;

import java.util.Optional;

public interface IUserInfoService extends IGeneralService<UserInfo,Long> {
    boolean existsByUsernameIgnoreCase(String username);

    UserInfo findUserInfoByUserId(Long userId);
}
