package com.bss.data.repos;

import com.bss.data.entities.Like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    // Add custom queries if needed
}