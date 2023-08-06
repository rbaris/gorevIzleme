package com.example.gorevizleme.Repos;

import com.example.gorevizleme.Models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    UserRole findByroleName(String roleName);
    UserRole findByRoleID(Long id);
}
