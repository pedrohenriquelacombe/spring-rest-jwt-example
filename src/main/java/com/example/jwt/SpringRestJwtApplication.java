package com.example.jwt;

import com.example.jwt.model.RoleModel;
import com.example.jwt.model.UserModel;
import com.example.jwt.repository.RoleRepository;
import com.example.jwt.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringRestJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestJwtApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(RoleRepository roleRepository, UserService userService) {
        return args -> {
            roleRepository.save(new RoleModel("ROLE_USER"));
            roleRepository.save(new RoleModel("ROLE_ADMIN"));
            roleRepository.save(new RoleModel("ROLE_MASTER"));

            userService.save(new UserModel("Ana", "ana@gmail.com", "123456"));
            userService.save(new UserModel("Pedro", "pedro@gmail.com", "123456"));
            userService.save(new UserModel("Fuskinha", "fuskinha@gmail.com", "123456"));

            userService.addRoleToUser("ana@gmail.com", "ROLE_USER");
            userService.addRoleToUser("pedro@gmail.com", "ROLE_ADMIN");
            userService.addRoleToUser("fuskinha@gmail.com", "ROLE_MASTER");
        };
    }

}
