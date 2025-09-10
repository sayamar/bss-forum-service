package com.bss.api.controller;

import com.bss.api.request_responses.UserDTO;
import com.bss.data.entities.User;
import com.bss.data.repos.UserRepository;
import com.bss.service.mappers.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userRequest) {
        log.info("userRequest :: {}",userRequest.getEmail());
        if(userRequest.getEmail() != null && userRequest.getPassword() != null) {
            UserDTO user = userMapper.toRequest(userRepository.findByEmail(userRequest.getEmail())
                    .orElseGet(User::new));
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(new UserDTO(), HttpStatus.BAD_REQUEST);
    }
}
