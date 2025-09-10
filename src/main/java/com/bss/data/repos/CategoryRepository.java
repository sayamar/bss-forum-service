package com.bss.data.repos;

import com.bss.data.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Check if a category with the same name exists
    boolean existsByName(String name);

    // ✅ That’s it — keep CategoryRepository only about categories
    // Don’t fetch posts here (we handle posts in PostRepository)
}
