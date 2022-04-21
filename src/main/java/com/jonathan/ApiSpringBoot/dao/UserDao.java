package com.jonathan.ApiSpringBoot.dao;

import com.jonathan.ApiSpringBoot.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<Users, Long> {
    
    Users findByUsername(String username);
}
