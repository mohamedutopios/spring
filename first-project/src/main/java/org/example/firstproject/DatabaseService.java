package org.example.firstproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseService {

    @Autowired
    private String databaseConnection;

    public void printConnection() {
        System.out.println(databaseConnection);
    }
}
