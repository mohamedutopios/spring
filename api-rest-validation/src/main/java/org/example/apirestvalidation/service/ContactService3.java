package org.example.apirestvalidation.service;

import org.example.apirestvalidation.model.Contact2;
import org.example.apirestvalidation.model.Contact3;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService3 {

    private final List<Contact3> contacts = new ArrayList<>();

    public ContactService3() {
        // Initial sample data
        contacts.add(new Contact3("ABC123", "John", "Doe", "john.doe@example.com", "1234567890"));
        contacts.add(new Contact3("DEF456", "Jane", "Smith", "jane.smith@example.com", "0987654321"));
    }

    public List<Contact3> getAllContacts() {
        return contacts;
    }

    public Contact3 addContact(Contact3 contact) {
        contacts.add(contact);
        return contact;
    }

    public Contact3 updateContact(String id, Contact3 updatedContact) {
        Contact3 existingContact = contacts.stream()
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
