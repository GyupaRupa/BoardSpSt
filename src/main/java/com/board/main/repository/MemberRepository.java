package com.board.main.repository;

import com.board.main.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<org.springframework.security.core.userdetails.User> findByUserId(String userId);
}