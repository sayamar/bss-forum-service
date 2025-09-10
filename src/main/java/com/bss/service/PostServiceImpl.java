package com.bss.service;

import com.bss.api.request_responses.PostDTO;
import com.bss.data.entities.Category;
import com.bss.data.entities.Post;
import com.bss.data.repos.PostRepository;
import com.bss.service.mappers.PostMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private EntityManager entityManager;
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public  List<Category> getPaginatedCategoriesWithPostsAndUsers(int pageNumber,
                                                                         int pageSize) {
        if (pageNumber <= 0) {
            throw new IllegalArgumentException("Page number must be greater than 0.");
        }

        String jpql = "SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.posts p LEFT JOIN FETCH p.user";
        TypedQuery<Category> query = entityManager.createQuery(jpql, Category.class);

        // Pagination
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    public List<PostDTO> findAllWithUserAndCategory() {
        List<Post> posts = postRepository.findAllWithUserAndCategory();

        // Sort posts by createdAt descending
        posts.sort((p1, p2) -> p2.getCreatedAt().compareTo(p1.getCreatedAt()));

        // Sort comments in descending order, handle null comments
        posts.forEach(post -> {
            if (post.getComments() != null) {
                post.getComments().sort((c1, c2) -> c2.getCreatedAt().compareTo(c1.getCreatedAt()));
            } else {
                post.setComments(new ArrayList<>()); // initialize empty list if null
            }
        });

        return postMapper.getAllPosts(posts);
    }

}
