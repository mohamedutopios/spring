package org.example.apirestvalidation.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.apirestvalidation.validation.CreateGroup;
import org.example.apirestvalidation.validation.UpdateGroup;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact2 {

    @NotBlank(groups = {CreateGroup.class}, message = "ID cannot be blank")
    @Pattern(regexp = "[A-Z]{3}[0-9]{3}", groups = {CreateGroup.class, UpdateGroup.class}, message = "ID must follow the pattern: 3 uppercase letters followed by 3 digits (e.g., ABC123)")
    private String id;

    @NotBlank(groups = {CreateGroup.class, UpdateGroup.class}, message = "First name is required")
    @Size(min = 2, max = 50, groups = {CreateGroup.class, UpdateGroup.class}, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(groups = {CreateGroup.class, UpdateGroup.class}, message = "Last name is required")
    @Size(min = 2, max = 50, groups = {CreateGroup.class, UpdateGroup.class}, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(groups = {CreateGroup.class, UpdateGroup.class}, message = "Email is required")
    @Email(groups = {CreateGroup.class, UpdateGroup.class}, message = "Email must be valid")
    private String email;

    @Pattern(regexp = "\\d{10}", groups = {CreateGroup.class, UpdateGroup.class}, message = "Phone number must be exactly 10 digits")
    private String phone;


}
