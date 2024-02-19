package com.example.inventorymanagement.controller.restController.authentication;

import com.example.inventorymanagement.config.JwtUtil;
import com.example.inventorymanagement.controller.restController.authentication.response.AuthResponse;
import com.example.inventorymanagement.model.await.ERole;
import com.example.inventorymanagement.model.await.User;
import com.example.inventorymanagement.model.await.UserInfo;
import com.example.inventorymanagement.model.dto.authDTO.request.LoginRequest;
import com.example.inventorymanagement.model.dto.authDTO.request.RegisterRequest;
import com.example.inventorymanagement.repository.UserInfoRepository;
import com.example.inventorymanagement.repository.UserRepository;
import com.example.inventorymanagement.service.user.IUserInfoService;
import com.example.inventorymanagement.service.user.IUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

import static com.example.inventorymanagement.model.await.ERole.ROLE_ADMIN;
import static com.example.inventorymanagement.model.await.ERole.ROLE_STAFF;


@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthResController {
    @Autowired
    private final IUserService userService;
    @Autowired
    private final IUserInfoService userInfoService;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtUtil jwtUtil;
    @Autowired
    private final AuthenticationManager authenticationManager;
    private final String SECRET = "baongaymengongbaongaymetrongbaongaymemongconchaodoiaptrongdaylongcochangtiengcuoicuamothainhidanglondan";

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        if (userService.checkEmail(request)) {
            return ResponseEntity
                    .status(
                            HttpStatus
                                    .UNAUTHORIZED
                    )
                    .body("There is already an account registered with the same email");
        }
        if (userService.checkUsername(request)) {
            return ResponseEntity
                    .status(
                            HttpStatus
                                    .UNAUTHORIZED
                    )
                    .body("There is already an account registered with the same username");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setFullName(request.getFullName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.save(user);
        UserInfo userInfo = new UserInfo();

        userInfo.setUser(user);
        userInfo.setEmail(request.getEmail());
        userInfo.setFullName(request.getFullName());
        userInfo.setUsername(request.getUsername());
        userInfo.setPhone(request.getPhone());
        userInfoService.save(userInfo);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {

        Optional<User> user = userService.findByUsername(request.getUsername());
        UserInfo userInfo= userInfoService.findUserInfoByUserId(user.get().getId());
        if (user.isPresent()) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
                String token = jwtToken(userInfo.getEmail());
                AuthResponse authResponse = new AuthResponse();
                authResponse.setJwt(token);
                authResponse.setIsAdmin(user.get().getRole().equals(ROLE_ADMIN));
                return ResponseEntity.ok(authResponse);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username or Password Incorrect");
    }
    private String jwtToken(String username) {
        long expiredTime = 1000L * 60 * 60 * 24 * 30;
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiredTime))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
