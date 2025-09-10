package com.bss.service.mappers;


import com.bss.api.request_responses.CommentDTO;
import com.bss.api.request_responses.CommentRequest;
import com.bss.data.entities.Comment;
import com.bss.data.entities.Post;
import com.bss.data.entities.User;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

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
    public CommentDTO toDTO(Comment comment) {
        if (comment == null) {
            return null;
        }

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setPostId(comment.getPost() != null ? comment.getPost().getPostId() : null);
        commentDTO.setUserId(comment.getUser() != null ? comment.getUser().getUserId() : null);
        commentDTO.setComment(comment.getContent());
        if (comment.getUser() != null) {
            commentDTO.setUsername(comment.getUser().getUsername());
        }
        if(comment.getCreatedAt() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            commentDTO.setCreatedAt(comment.getCreatedAt().format(formatter));
        }


        return commentDTO;
    }
}

