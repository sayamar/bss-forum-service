package com.bss.service.mappers;

import com.bss.api.request_responses.UserDTO;
import com.bss.data.entities.User;
import org.springframework.stereotype.Component;

//import org.springframework.security.crypto.password.PasswordEncoder;
@Component
public class UserMapper {

//    private final PasswordEncoder passwordEncoder;
//
//    public UserMapper(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

    public User toEntity(UserDTO request) {
        if (request == null) {
            return null;
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPasswordHash(request.getPassword()); // hash password
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        // createdAt and updatedAt can be set automatically using JPA @PrePersist (if configured) or manually here
        return user;
    }

    public UserDTO toRequest(User user) {
        if (user == null) {
            return new UserDTO();
        }
        UserDTO request = new UserDTO();
        request.setUsername(user.getUsername());
        // Usually you don’t send password back in responses, omit it here
        request.setFullName(user.getFullName());
        request.setEmail(user.getEmail());
        request.setUserId(String.valueOf(user.getUserId()));
        return request;
    }
}

