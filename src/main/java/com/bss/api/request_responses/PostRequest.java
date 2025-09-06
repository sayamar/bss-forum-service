package com.bss.api.request_responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {

    private Long userId;  // ID of the User authoring the post

    private String title;

    private String content;

    private Long categoryId;


}
