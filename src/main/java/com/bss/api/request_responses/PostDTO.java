package com.bss.api.request_responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostDTO {

    private Long postId;
    private String title;
    private String content;
    private String username;       // author
    private String categoryName;
    private int likesCount;
    private boolean solved;
    private List<CommentDTO> comments; // ✅ attach comments
    private LocalDateTime createdAt;
}
