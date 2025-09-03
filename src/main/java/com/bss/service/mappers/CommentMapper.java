package com.bss.service.mappers;


import com.bss.api.request_responses.CommentRequest;
import com.bss.data.entities.Comment;
import com.bss.data.entities.Post;
import com.bss.data.entities.User;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    /**
     * Converts CommentRequest DTO to Comment entity.
     * User and Post entities should be fetched beforehand from DB and passed here.
     */
    public Comment toEntity(CommentRequest commentRequest, Post post, User user) {
        if (commentRequest == null || post == null || user == null) {
            return null;
        }

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUser(user);
        comment.setContent(commentRequest.getContent());
        // createdAt and updatedAt can be handled by JPA or set here as needed
        return comment;
    }

    /**
     * Converts Comment entity to CommentRequest DTO.
     * Useful if you want to send comment info in the same format.
     */
    public CommentRequest toRequest(Comment comment) {
        if (comment == null) {
            return null;
        }

        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setPostId(comment.getPost() != null ? comment.getPost().getPostId() : null);
        commentRequest.setUserId(comment.getUser() != null ? comment.getUser().getUserId() : null);
        commentRequest.setContent(comment.getContent());

        return commentRequest;
    }
}

