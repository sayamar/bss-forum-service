package com.bss.data.repos;

import com.bss.data.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // You can define custom methods if needed. For example:
    boolean existsByName(String name); // checks if a category with the same name exists

    @Query("SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.posts p LEFT JOIN FETCH p.user WHERE c.name LIKE %:filter%")
    List<Category> findAllWithPostsAndUsers(@Param("filter") String filter);

    // Or simply fetch all categories with posts (no filter)
    @Query("SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.posts p LEFT JOIN FETCH p.user")
    List<Category> findAllWithPostsAndUsers();
}
