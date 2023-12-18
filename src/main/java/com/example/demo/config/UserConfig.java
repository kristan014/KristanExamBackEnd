package com.example.demo.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunnerUser(UserRepo userRepo) {
        return args -> {
            User kristan = new User(
                    "Kristan",
                    "Diaz",
                    "Balin",
                    "091231231",
                    "kristanbalin@gmail.com"

                    );

             User kristan2 = new User(
                    "Kristan2",
                    "Diaz",
                    "Balin",
                    "091231231411",
                    "kristanbalin2@gmail.com"

                    );                    

        
            userRepo.saveAll(
                List.of(kristan,kristan2)
            );

        };
    }

}
