package com.example.inventorymanagement.model.dto.authDTO.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors
public class UserInfoSaveResponse {
    private Long id;
    private Long userId;

    private String username;
    private String password;

    private String fullName;
    private String email;
    private String phone;

}
