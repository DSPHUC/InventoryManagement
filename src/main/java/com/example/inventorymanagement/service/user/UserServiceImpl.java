package com.example.inventorymanagement.service.user;

import com.example.inventorymanagement.model.await.ERole;
import com.example.inventorymanagement.model.await.User;
import com.example.inventorymanagement.model.dto.authDTO.request.RegisterRequest;
import com.example.inventorymanagement.repository.UserRepository;
import com.example.inventorymanagement.security.service.UserPrinciple;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements IUserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }


    @Override
    public void save(User user) {
        user.setRole(ERole.ROLE_STAFF);
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public boolean checkUsername(RegisterRequest request) {
        return userRepository.existsByUsernameIgnoreCase(request.getUsername());
    }

    @Override
    public boolean checkEmail(RegisterRequest request) {
        return userRepository.existsByEmailIgnoreCase(request.getEmail());
    }
    @Override
    public Optional<User> findByUsername(String username) {

        return userRepository.findByUsername(username);
    }


    @Override
    public UserDetails loadUserByUsername(String username)  {

//        User user = userRepository.findByUsernameIgnoreCaseOrEmailIgnoreCase(username, username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not Exist"));
//        var role = new ArrayList<SimpleGrantedAuthority>();
//        role.add(new SimpleGrantedAuthority(user.getRole().toString()));
//
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), role);
        return UserPrinciple.build(userRepository.findByUsername(username).get());
    }

}
