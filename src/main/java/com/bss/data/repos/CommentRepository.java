package com.bss.data.repos;

import com.bss.data.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Get all comments for a specific post
    List<Comment> findByPost_PostIdOrderByCreatedAtDesc(Long postId);

    // Optional: get all comments by a user
    List<Comment> findByUser_UserId(Long userId);
}
