package com.shopease.ecommerce.payload;

import lombok.*;
import org.springframework.stereotype.Component;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CategoryDTO {
    private Long categoryId;
    private String categoryName;
}
