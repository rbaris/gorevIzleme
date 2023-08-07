package com.example.gorevizleme;

import com.example.gorevizleme.Models.User;
import com.example.gorevizleme.Models.UserRole;
import com.example.gorevizleme.Services.UserRoleService;
import com.example.gorevizleme.Services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class GorevIzlemeApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(GorevIzlemeApplication.class, args);
        System.out.println(Arrays.asList(applicationContext.getBeanDefinitionNames()));

    }


    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }


    
    @Bean
    CommandLineRunner run(UserService userService, UserRoleService userRoleService){
        return args -> {

            userRoleService.saveRole(new UserRole(1L,"ROLE_ADMIN"));
            userRoleService.saveRole(new UserRole(2L,"ROLE_PILOT"));
            userRoleService.saveRole(new UserRole(3L,"ROLE_COPILOT"));

            userService.saveUser(new User(null,"rbaris","rbaris@email.com","123baris","Barış","Atakcı","0531987452",new ArrayList<>()));
            userService.saveUser(new User(null,"bramazan","ramazan@email.com","12345","Ramazan","Atakcı","053197452563",new ArrayList<>()));

            userService.addRoleToUser("rbaris","ROLE_ADMIN");
            userService.addRoleToUser("rbaris","ROLE_PILOT");
            userService.addRoleToUser("bramazan","ROLE_COPILOT");
        };
    }
}
