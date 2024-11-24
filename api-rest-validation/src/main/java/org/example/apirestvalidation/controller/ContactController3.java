package org.example.apirestvalidation.controller;

import jakarta.validation.Valid;
import org.example.apirestvalidation.model.Contact2;
import org.example.apirestvalidation.model.Contact3;
import org.example.apirestvalidation.service.ContactService2;
import org.example.apirestvalidation.service.ContactService3;
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
@RequestMapping("/api/contacts3")
public class ContactController3 {

    private final ContactService3 contactService3;

    public ContactController3(ContactService3 contactService3) {
        this.contactService3 = contactService3;
    }

    @PostMapping
    public ResponseEntity<?> addContact(@Valid @RequestBody Contact3 contact, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        Contact3 createdContact = contactService3.addContact(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContact);
    }

}

