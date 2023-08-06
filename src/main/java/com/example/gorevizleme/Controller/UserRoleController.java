package com.example.gorevizleme.Controller;

import com.example.gorevizleme.Models.UserRole;
import com.example.gorevizleme.Services.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/roles")
public class UserRoleController {
    private final UserRoleService userRoleService;

    @GetMapping()
    public ResponseEntity<?> getallRoles(){
        return ResponseEntity.ok(userRoleService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserRole>> getRoleByid(@RequestBody Long id){
        return ResponseEntity.ok(userRoleService.getRolewithId(id));
    }
    @PostMapping()
    public ResponseEntity<UserRole> saveRole(@RequestBody UserRole userRole){
        return ResponseEntity.ok(userRoleService.saveRole(userRole));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Optional<UserRole>> updateRole(@RequestBody Long id, UserRole newRole){
        return ResponseEntity.ok(userRoleService.updateRole(id, newRole));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<UserRole>> deleteRole(@RequestBody Long id){
        return ResponseEntity.ok(userRoleService.deleteRole(id));
    }
}
