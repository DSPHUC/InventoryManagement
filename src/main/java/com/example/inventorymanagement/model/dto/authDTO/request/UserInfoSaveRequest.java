package com.example.inventorymanagement.model.dto.authDTO.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoSaveRequest {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
}
