package org.example.apirestvalidation.controller;

import org.example.apirestvalidation.model.Contact2;
import org.example.apirestvalidation.service.ContactService2;
import org.example.apirestvalidation.validation.CreateGroup;
import org.example.apirestvalidation.validation.UpdateGroup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/contacts2")
public class ContactController2 {

    private final ContactService2 contactService2;

    public ContactController2(ContactService2 contactService2) {
        this.contactService2 = contactService2;
    }

    // GET: Retrieve all contacts
    @GetMapping
    public List<Contact2> getAllContacts() {
        return contactService2.getAllContacts();
    }

    // POST: Add a new contact (validation for CreateGroup)
    @PostMapping
    public ResponseEntity<?> addContact(@Validated(CreateGroup.class) @RequestBody Contact2 contact2, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        Contact2 createdContact = contactService2.addContact(contact2);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContact);
    }

    // PUT: Update a contact (validation for UpdateGroup)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(
            @PathVariable String id,
            @Validated(UpdateGroup.class) @RequestBody Contact2 contact,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        Contact2 updatedContact = contactService2.updateContact(id, contact);
        return ResponseEntity.ok(updatedContact);
    }
}

