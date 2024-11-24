package org.example.apirestvalidation.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ContactIdValidator implements ConstraintValidator<ValidContactId, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false; // ID ne peut pas être vide ou null
        }

        // Vérifie si l'ID correspond au format "C-" suivi de 3 chiffres
        return value.matches("C-\\d{3}");
    }
}
