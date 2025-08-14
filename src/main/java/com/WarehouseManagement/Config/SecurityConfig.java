package com.WarehouseManagement.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf(csrf -> csrf.disable())
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/users/register", "/users/all","/users/login","/users/admin/create", "/users/by-username").permitAll()
//                .anyRequest().authenticated()
//            );
//        return http.build();
//    }
//    
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf(csrf -> csrf.disable()) // ✅ Disable CSRF for Postman testing
//            .formLogin(form -> form.disable()) // ✅ Disable Spring's default login page
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers(
//                    "/users/register",
//                    "/users/login",
//                    "/users/admin/create",
//                    "/users/all",
//                    "/users/by-username"
//                ).permitAll()
//                .anyRequest().authenticated()
//            );
//        return http.build();
//    }

    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // 🔓 Disable CSRF for Postman testing
            .formLogin(form -> form.disable()) // 🔓 Disable Spring's default login page
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/users/register",
                    "/users/login",
                    "/users/admin/create",
                    "/users/all",
                    "/users/by-username"
                ).permitAll() // ✅ Allow these endpoints without authentication
                .anyRequest().authenticated() // 🔐 Protect everything else
            );
        return http.build();
    }
}
