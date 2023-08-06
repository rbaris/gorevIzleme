package com.example.gorevizleme.Repos;

import com.example.gorevizleme.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserID(Long id);
    User findByusername(String username);
    List<User> findAllByRoles_RoleName(String rolename);

    List<User> findAllByRoles(Role role);
}
