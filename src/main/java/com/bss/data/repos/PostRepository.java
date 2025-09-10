package com.bss.data.repos;

import com.bss.data.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // Fetch all posts with their category + user (efficiently)
    @Query("SELECT p FROM Post p JOIN FETCH p.user JOIN FETCH p.category")
    List<Post> findAllWithUserAndCategory();

    // Fetch posts by categoryId
    @Query("SELECT p FROM Post p JOIN FETCH p.user JOIN FETCH p.category c WHERE c.categoryId = :categoryId")
    List<Post> findAllByCategoryId(@Param("categoryId") Long categoryId);

    // Fetch posts by userId
    @Query("SELECT p FROM Post p JOIN FETCH p.user u JOIN FETCH p.category WHERE u.userId = :userId")
    List<Post> findAllByUserId(@Param("userId") Long userId);
}
