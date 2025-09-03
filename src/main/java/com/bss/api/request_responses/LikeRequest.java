package com.bss.api.request_responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeRequest {

    private Long userId;

    private Long postId;     // nullable; mutually exclusive with commentId

    private Long commentId;  // nullable; mutually exclusive with postId

}