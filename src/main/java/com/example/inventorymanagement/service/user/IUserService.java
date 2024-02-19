package com.example.inventorymanagement.service.user;

import com.example.inventorymanagement.model.await.User;
import com.example.inventorymanagement.model.dto.authDTO.request.RegisterRequest;
import com.example.inventorymanagement.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface IUserService extends IGeneralService<User, Long> {

    boolean checkUsername(RegisterRequest request);

    boolean checkEmail(RegisterRequest request);


    Optional<User> findByUsername(String username);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
