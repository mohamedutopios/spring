package org.example.firstproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FirstProjectApplication {



    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(FirstProjectApplication.class, args);

        // Récupérer le bean DatabaseService
        DatabaseService databaseService = context.getBean(DatabaseService.class);

        EmailService emailService = context.getBean(EmailService.class);

        // Utiliser le service pour envoyer un email
        emailService.sendEmail("Hello from Spring Boot!");

        // Imprimer la connexion
        databaseService.printConnection();

    }

}
