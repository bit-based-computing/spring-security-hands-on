package com.foysal.jwt.initialize.data;

import com.foysal.jwt.entity.Role;
import com.foysal.jwt.entity.User;
import com.foysal.jwt.repository.RoleRepository;
import com.foysal.jwt.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DataInitializer {

    @Bean
    CommandLineRunner init(
            UserRepository userRepo,
            RoleRepository roleRepo,
            PasswordEncoder encoder
    ) {
        return args -> {

            Role userRole = roleRepo.save(new Role("ROLE_USER"));
            Role adminRole = roleRepo.save(new Role("ROLE_ADMIN"));

            User user = new User();
            user.setUsername("user");
            user.setPassword(encoder.encode("password"));
            user.setRoles(Set.of(userRole));

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("password"));
            admin.setRoles(Set.of(adminRole));

            userRepo.saveAll(List.of(user, admin));
        };
    }
}

