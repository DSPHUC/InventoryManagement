package com.example.inventorymanagement.model.dto.authDTO.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest implements Validator {

    private String fullName;

    private String phone;

    @NotEmpty(message = "Please enter your username")
    private String username;

    @NotEmpty(message = "Please enter your password")
    private String password;

    @NotEmpty(message = "Please enter your email")
    private String email;

    private String gender;


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterRequest registerRequest = (RegisterRequest) target;
        String username = registerRequest.username;
        String password = registerRequest.password;
        String email = registerRequest.email;
        if (username.length() < 5) {
            errors.rejectValue("username",
                    "username.length",
                    "Username must be at least 5 characters");
        }
        if (password.length() < 5) {
            errors.rejectValue("password",
                    "password.length",
                    "Password must be at least 5 characters");
        }
        if (email.isEmpty() ) {
            errors.rejectValue("email",
                    "email.isEmpty",
                    "Email cannot be blank");
        }
    }
}
