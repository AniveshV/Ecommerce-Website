package com.shopease.ecommerce.payload;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CategoryResponse {

    private List<CategoryDTO> categoryDTOList;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private boolean isLastPage;
}
