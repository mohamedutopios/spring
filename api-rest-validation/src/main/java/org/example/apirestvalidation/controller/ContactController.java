package org.example.apirestvalidation.controller;

import jakarta.validation.Valid;
import org.example.apirestvalidation.model.Contact;
import org.example.apirestvalidation.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // GET: Retrieve all contacts
    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    // POST: Add a new contact with validation
    @PostMapping
    public ResponseEntity<?> addContact(@Valid @RequestBody Contact contact, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Collect all validation errors
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        Contact createdContact = contactService.addContact(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContact);
    }
}

