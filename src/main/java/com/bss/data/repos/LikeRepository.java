package com.bss.data.repos;

import com.bss.data.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    // Get likes for a post
    List<Like> findByPost_PostId(Long postId);

    // Get likes for a comment
    List<Like> findByComment_CommentId(Long commentId);

    // Get likes by user
    List<Like> findByUser_UserId(Long userId);
}
