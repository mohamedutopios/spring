package org.example.apirestvalidation.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = ContactIdValidator.class) // Associe l'annotation au validateur
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidContactId {
    String message() default "Invalid Contact ID. Must start with 'C-' followed by 3 digits."; // Message par défaut
    Class<?>[] groups() default {}; // Groupes de validation
    Class<? extends Payload>[] payload() default {}; // Charge utile supplémentaire
}
