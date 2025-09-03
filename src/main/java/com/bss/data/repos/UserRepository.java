package com.bss.data.repos;


import com.bss.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // You can add custom query methods if needed, e.g.
    // Optional<User> findByUsername(String username);
    User findByUsernameAndPasswordHash(String username, String passwordHash);
    Optional<User> findByUsername(String username);

}