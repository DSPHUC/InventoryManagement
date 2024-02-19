package com.example.inventorymanagement.service.user;

import com.example.inventorymanagement.model.await.UserInfo;
import com.example.inventorymanagement.repository.UserInfoRepository;
import com.example.inventorymanagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserInfoServiceImpl implements IUserInfoService, UserDetailsService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserInfo> findAll() {
        return userInfoRepository.findAll();
    }


    @Override
    public UserInfo findById(Long id) {
        return userInfoRepository.findById(id).get();
    }

    @Override
    public void save(UserInfo userInfo) {
        userInfoRepository.save(userInfo);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public boolean existsByUsernameIgnoreCase(String username) {
        return userInfoRepository.existsByUsernameIgnoreCase(username);
    }

    @Override
    public UserInfo findUserInfoByUserId(Long userId) {
        return userInfoRepository.findUserInfoByUserId(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userInfoRepository.findUserInfoByEmail(username);

        var role = new ArrayList<SimpleGrantedAuthority>();
        role.add(new SimpleGrantedAuthority(user.getUser().getRole().toString()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                passwordEncoder.encode(user.getEmail()),
                role);
    }
}
