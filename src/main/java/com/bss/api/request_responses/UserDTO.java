package com.bss.api.request_responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String username;
    private String password;   // Accept plain password here, hash it later when mapping to Entity
    private String fullName;
    private String email;
    private String userId;

}