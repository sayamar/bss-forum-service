package com.bss.service.mappers;




import com.bss.api.request_responses.PostRequest;
import com.bss.data.entities.Category;
import com.bss.data.entities.Post;
import com.bss.data.entities.User;
import org.springframework.stereotype.Component;

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
        postRequest.setUserId(post.getUser() != null ? post.getUser().getUserId() : null);
        postRequest.setTitle(post.getTitle());
        postRequest.setContent(post.getContent());
        return postRequest;
    }
}
