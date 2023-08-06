package com.example.gorevizleme.Controller;

import com.example.gorevizleme.Models.User;
import com.example.gorevizleme.Models.UserRole;
import com.example.gorevizleme.Services.UserRoleService;
import com.example.gorevizleme.Services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserRoleService userRoleService;
    @GetMapping("/{id}")
    public ResponseEntity<User>getUserwithID(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @GetMapping()
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /*@PostMapping()
    public ResponseEntity<User> addUser(@RequestBody User user){
        return ResponseEntity.ok(userService.saveUser(user));
    }*/

    @PostMapping()
    public ResponseEntity<User> addnewUser(@RequestBody User newUser){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").toUriString());
        userService.saveUser(newUser);
        return ResponseEntity.created(uri).body(newUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().body("Kullanıcı başarıyla silindi !");
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User newUser) throws Exception {
        return ResponseEntity.ok(userService.updateUser(id,newUser));
    }
    @GetMapping("/{id}/roles")
    public ResponseEntity<?> getRoleofUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getroleofUserwithID(id));
    }
    @PostMapping("/{id}/roles")
    public ResponseEntity<?> addRoletoUser(@PathVariable Long id, @RequestBody UserRole userRole){
        User u = userService.getUserById(id);
        u.getRoles().add(userRole);
        userService.saveUser(u);
        return ResponseEntity.ok(u);
    }
    @DeleteMapping("/{id}/roles")
    public ResponseEntity<?> deleteRoletoUser(@PathVariable Long id,@RequestBody UserRole deleteuserRole){
        User u = userService.getUserById(id);
        u.getRoles().remove(deleteuserRole);
        userService.saveUser(u);
        return ResponseEntity.ok().body(deleteuserRole.roleName + " rolü "+u.username + " kullanıcısından başarıyla silindi.");
    }


}
