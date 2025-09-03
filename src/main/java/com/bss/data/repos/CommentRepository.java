package com.bss.data.repos;


import com.bss.data.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Add custom queries if needed
    List<Comment> findByPost_PostId(Long postId);
}