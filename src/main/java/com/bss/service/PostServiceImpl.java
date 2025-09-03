package com.bss.service;

import com.bss.data.entities.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private EntityManager entityManager;

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
}
