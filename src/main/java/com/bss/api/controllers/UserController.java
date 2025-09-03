package com.bss.api.controllers;




import com.bss.api.request_responses.UserRequest;
import com.bss.data.entities.User;
import com.bss.data.repos.UserRepository;
import com.bss.service.mappers.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {


    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @PostMapping
    public User createUser(@RequestBody UserRequest userRequest) {
        // Ideally hash the password here before saving
        return userRepository.save(userMapper.toEntity(userRequest));
    }
}

