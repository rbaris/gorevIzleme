package com.example.gorevizleme.Services;

import com.example.gorevizleme.Models.UserRole;
import com.example.gorevizleme.Repos.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRole saveRole(UserRole role)
    {
        userRoleRepository.save(role);
        return  role;
    }

    public Optional<UserRole> getRolewithId(Long id){
        return userRoleRepository.findById(id);
    }
    public List<UserRole> getAll()
    {

        return userRoleRepository.findAll();
    }
    public Optional<UserRole> deleteRole(Long id){
        var isRemoved = userRoleRepository.findById(id);
        userRoleRepository.deleteById(id);
        return isRemoved;
    }

    public Optional<UserRole> updateRole(Long id, UserRole newUserRole){
        return userRoleRepository.findById(id)
                .map(userRole -> {userRole.setRoleName(newUserRole.getRoleName());
                    return userRoleRepository.save(userRole);
                });
    }
}
