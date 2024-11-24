package org.example.firstproject;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class ProdEmailService implements EmailService {
    @Override
    public void sendEmail(String message) {
        System.out.println("Sending email in PROD: " + message);
    }
}
