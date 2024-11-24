package org.example.apirestvalidation.service;

import org.example.apirestvalidation.model.Contact2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService2 {

    private final List<Contact2> contacts = new ArrayList<>();

    public ContactService2() {
        // Initial sample data
        contacts.add(new Contact2("ABC123", "John", "Doe", "john.doe@example.com", "1234567890"));
        contacts.add(new Contact2("DEF456", "Jane", "Smith", "jane.smith@example.com", "0987654321"));
    }

    public List<Contact2> getAllContacts() {
        return contacts;
    }

    public Contact2 addContact(Contact2 contact) {
        contacts.add(contact);
        return contact;
    }

    public Contact2 updateContact(String id, Contact2 updatedContact) {
        Contact2 existingContact = contacts.stream()
                .filter(contact -> contact.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Contact not found"));
        existingContact.setFirstName(updatedContact.getFirstName());
        existingContact.setLastName(updatedContact.getLastName());
        existingContact.setEmail(updatedContact.getEmail());
        existingContact.setPhone(updatedContact.getPhone());
        return existingContact;
    }
}
