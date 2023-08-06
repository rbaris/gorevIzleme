package com.example.gorevizleme.Services;

import com.example.gorevizleme.Models.User;
import com.example.gorevizleme.Models.UserRole;
import com.example.gorevizleme.Repos.UserRepository;
import com.example.gorevizleme.Repos.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service

@RequiredArgsConstructor
@Transactional
@Slf4j //simple logging api
public class UserService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserRoleRepository userRoleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getAllUsers()
    {
        log.info("Get All Users...");
        return userRepository.findAll();
    }
    public User saveUser(User user)
    {
        log.info("Saving user...");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }
    public User getUser(String username)
    {
        return userRepository.findByusername(username);
    }


    public List<User> getAllUserByRole(String rolename)
    {
        return userRepository.findAllByRoles_RoleName(rolename);

    }


    public UserRole saveRole(UserRole role)
    {
        log.info("Saving role...");
        userRoleRepository.save(role);
        return role;
    }

    public void addRoleToUser(String username, String roleName)
    {
        log.info("Adding {} role to user {}...", roleName,username);
        User user = getUser(username);
        UserRole role = userRoleRepository.findByroleName(roleName);
        user.getRoles().add(role);

    }

    public User addRoleToUser(Long userId, String roleName)
    {
        User user = userRepository.findByUserID(userId);
        UserRole role = userRoleRepository.findByroleName(roleName);
        user.getRoles().add(role);
        userRepository.save(user);
        log.info("Adding {} role to user {}...", roleName,userId);

        return user;

    }
    public Collection<UserRole> getroleofUserwithID(Long id){
        User user = getUserById(id);
        return user.getRoles();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByusername(username);
        if(user == null)
            throw new UsernameNotFoundException("Username not found!");
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(userRole -> authorities.add(new SimpleGrantedAuthority(userRole.roleName)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user == null)
            throw new UsernameNotFoundException("Username not found!");
        return user.get();
    }

    public void deleteUser(Long id){
        Optional<User> user = userRepository.findById(id);
        userRepository.deleteById(id);
    }


    public User updateUser(Long id,User newUser) throws Exception {
        if(id==newUser.getUserID()){
            log.info("Updating user...");
            userRepository.save(newUser);
            return newUser;
        }
        else{
            throw new Exception("not updated");
        }
    }


    public Collection<UserRole> getUsersRolesByUserId(Long id){
        User user = userRepository.findByUserID(id);

        return user.getRoles();
    }



}
