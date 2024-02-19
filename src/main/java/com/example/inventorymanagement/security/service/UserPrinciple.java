package com.example.inventorymanagement.security.service;

import com.example.inventorymanagement.model.await.ERole;
import com.example.inventorymanagement.model.await.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrinciple  implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> roles;

    public UserPrinciple(String username, String password,
                         Collection<? extends GrantedAuthority> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public static UserPrinciple build(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        ERole role = user.getRole();
            authorities.add(new SimpleGrantedAuthority(role.name));
        return new UserPrinciple(user.getUsername(),
                user.getPassword(),authorities);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
