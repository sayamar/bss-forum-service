package com.bss.data.repos;

import com.bss.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by username + password hash (for login)
    User findByUsernameAndPasswordHash(String username, String passwordHash);

    // Find user by username (for registration / validation)
    Optional<User> findByUsername(String username);

    // Find user by email
    Optional<User> findByEmail(String email);
}
