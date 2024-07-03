package com.shopease.ecommerce.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.aspectj.lang.annotation.RequiredTypes;

import java.util.Optional;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    @NotEmpty
    private String productName;
    @NotEmpty
    private String description;
    @Positive
    private int quantity;
    @DecimalMax("100.0")
    @DecimalMin("0.1")
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id" )
    @NonNull
    private Category category;

}
