package org.example.firstproject;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DevEmailService implements EmailService {
    @Override
    public void sendEmail(String message) {
        System.out.println("Sending email in DEV: " + message);
    }
}
