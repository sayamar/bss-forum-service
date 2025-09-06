package com.bss.api.controller;

import com.bss.api.request_responses.UserRequest;
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

    @PostMapping("/login")
    public ResponseEntity<UserRequest> login(@RequestBody UserRequest userRequest) {
        log.info("userRequest :: {}",userRequest.getEmail());
        if(userRequest.getEmail() != null && userRequest.getPassword() != null) {
            // service call
            userRequest.setUserId("100");
            return new ResponseEntity<>(userRequest, HttpStatus.OK);
        }
        return new ResponseEntity<>(new UserRequest(), HttpStatus.BAD_REQUEST);
    }
}
