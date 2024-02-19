package com.example.inventorymanagement.model.dto.authDTO.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginRequest {
    private String username;
    private String password;
}
