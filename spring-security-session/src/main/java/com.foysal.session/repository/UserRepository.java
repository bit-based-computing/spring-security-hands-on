package com.foysal.session.repository;

import com.foysal.session.entity.Role;
import com.foysal.session.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
