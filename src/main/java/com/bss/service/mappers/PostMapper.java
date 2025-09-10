package com.bss.service.mappers;




import com.bss.api.request_responses.CommentDTO;
import com.bss.api.request_responses.PostDTO;
import com.bss.api.request_responses.PostRequest;
import com.bss.data.entities.Category;
import com.bss.data.entities.Comment;
import com.bss.data.entities.Post;
import com.bss.data.entities.User;
import com.bss.util.Constants;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostMapper {

    /**
     * Converts PostRequest DTO to Post entity.
     * Note: You need to load User entity separately (e.g., from DB) and pass it here.
     */
    public Post toEntity(PostRequest postRequest, User user, Category category) {
        if (postRequest == null || user == null) {
            return null;
        }

        Post post = new Post();
        post.setUser(user);
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setCategory(category);
        // createdAt and updatedAt will be set by JPA or you can set here manually if needed
        return post;
    }

    /**
     * Converts Post entity to PostRequest DTO.
     * Typically used if you want to send back data to client in same format or for internal uses.
     */
    public PostRequest toRequest(Post post) {
        if (post == null) {
            return null;
        }

        PostRequest postRequest = new PostRequest();
        Long userId = post.getUser() != null ? post.getUser().getUserId() : null;
        postRequest.setUserId(String.valueOf(userId));
        postRequest.setTitle(post.getTitle());
        postRequest.setContent(post.getContent());
        return postRequest;
    }

    public List<PostDTO> getAllPosts( List<Post> posts) {

        return posts.stream().map(p -> {
            PostDTO dto = new PostDTO();
            dto.setPostId(p.getPostId());
            dto.setTitle(p.getTitle());
            dto.setContent(p.getContent());
            dto.setUsername(p.getUser().getUsername());
            dto.setCategoryName(p.getCategory().getName());
            dto.setLikesCount(p.getLikesCount());
            dto.setCreatedAt(p.getCreatedAt());
            if(p.getStatus().equals(Constants.Solved)) {
                dto.setSolved(true);
            }
            // ✅ attach comments
            dto.setComments(
                    p.getComments() != null
                            ? p.getComments().stream().map(this::mapCommentToDTO).collect(Collectors.toList())
                            : List.of()
            );
            return dto;
        }).collect(Collectors.toList());
    }

    private CommentDTO mapCommentToDTO(Comment c) {
        CommentDTO dto = new CommentDTO();
        dto.setCommentId(c.getCommentId());
        dto.setComment(c.getContent());
        dto.setUsername(c.getUser().getUsername());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dto.setCreatedAt(c.getCreatedAt().format(formatter));
        return dto;
    }
}
