package org.example.apirestexception.model;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @NotNull(message = "ID is required")
    private Integer id;

    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 0, message = "Price must be positive")
    private Double price;


}

