package com.bss.api.request_responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CommentDTO {
    private Long commentId;
    private Long postId;
    private Long userId;
    private String username; // Optional: send username for convenience
    private String comment;
    private String createdAt;

    // getters & setters
}
