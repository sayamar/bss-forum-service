package com.bss.api.request_responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeDTO {

    private Long userId;

    private Long postId;     // nullable; mutually exclusive with commentId

    private Long likeId;  // nullable; mutually exclusive with postId

    public LikeDTO(Long userId, Long postId, Long likeId) {
        this.userId = userId;
        this.postId = postId;
        this.likeId = likeId;
    }

    public LikeDTO() {

    }
}