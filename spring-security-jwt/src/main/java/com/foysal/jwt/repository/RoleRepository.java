package com.foysal.jwt.repository;

import com.foysal.jwt.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {}

