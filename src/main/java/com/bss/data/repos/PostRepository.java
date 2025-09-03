package com.bss.data.repos;


import com.bss.data.entities.Post;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

     // Add custom queries if needed
    List<Post> findByCategory_CategoryId(Long categoryId);

}
