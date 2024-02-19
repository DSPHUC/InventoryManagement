package com.example.inventorymanagement.security.config;

import com.example.inventorymanagement.security.jwt.CustomAccessDeniedHandler;
import com.example.inventorymanagement.security.jwt.JwtAuthenticationTokenFilter;
import com.example.inventorymanagement.security.jwt.RestAuthenticationEntryPoint;
import com.example.inventorymanagement.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private IUserService userService;

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService((UserDetailsService) userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Tắt CSRF protection
                .authorizeHttpRequests(authorize ->
                                authorize
                                        .requestMatchers("/assets/**").permitAll()
                                        .requestMatchers("/jquery/**").permitAll()
                                        .requestMatchers("/myCustom/**").permitAll()
                                        .requestMatchers("/api/auth/**").permitAll()
                                        .requestMatchers("/api/**").permitAll() //xóa sau
                                        .requestMatchers("/login/**").permitAll()
                                        .requestMatchers("/home/**").permitAll()
                                        .requestMatchers("/register/**").permitAll()
//                                .requestMatchers("/api/**").hasAnyRole("USER", "ADMIN")
//                                .requestMatchers("/home").hasAnyRole("USER", "ADMIN")
                                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin(form ->
                        form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/home")
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }
}
