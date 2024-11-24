package org.example.apirestvalidation.service;

import org.example.apirestvalidation.model.Contact;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

    private final List<Contact> contacts = new ArrayList<>();

    public ContactService() {
        // Initial sample data
        contacts.add(new Contact("1", "John", "Doe", "john.doe@example.com", "1234567890"));
        contacts.add(new Contact("2", "Jane", "Smith", "jane.smith@example.com", "0987654321"));
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }

    public Contact addContact(Contact contact) {
        contacts.add(contact);
        return contact;
    }
}

