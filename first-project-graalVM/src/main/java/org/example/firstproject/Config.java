package org.example.firstproject;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class Config {
    @Bean
    @Profile("dev")
    public String devDatabaseConnection() {
        return "Connected to DEV database";
    }

    @Bean
    @Profile("prod")
    public String prodDatabaseConnection() {
        return "Connected to PROD database";
    }
}
