package com.bss.api.request_responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {

    private Long postId;    // Reference to Post entity

    private Long userId;    // Reference to User entity

    private String content; // Comment content

}