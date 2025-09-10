package com.bss.service.mappers;




import com.bss.api.request_responses.LikeDTO;
import com.bss.data.entities.Comment;
import com.bss.data.entities.Like;
import com.bss.data.entities.Post;
import com.bss.data.entities.User;
import org.springframework.stereotype.Component;

@Component
public class LikeMapper {

    /**
     * Converts LikeRequest DTO to Like entity.
     * Requires User entity and either Post or Comment entity (depending on which is liked).
     * Pass null for post/comment if not applicable.
     */
    public Like toEntity(LikeDTO likeRequest, User user, Post post, Comment comment) {
        if (likeRequest == null || user == null) {
            return null;
        }

        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        like.setComment(comment);
        // createdAt can be set by JPA or here if needed
        return like;
    }

    /**
     * Converts Like entity to LikeRequest DTO.
     */
    public LikeDTO toRequest(Like like) {
        if (like == null) {
            return null;
        }

        LikeDTO likeRequest = new LikeDTO();
        likeRequest.setUserId(like.getUser() != null ? like.getUser().getUserId() : null);
        likeRequest.setPostId(like.getPost() != null ? like.getPost().getPostId() : null);
    //    likeRequest.setCommentId(like.getComment() != null ? like.getComment().getCommentId() : null);

        return likeRequest;
    }
}
