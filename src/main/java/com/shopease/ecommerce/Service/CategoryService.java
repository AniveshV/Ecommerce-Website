package com.shopease.ecommerce.Service;

import com.shopease.ecommerce.Model.Category;
import com.shopease.ecommerce.payload.CategoryDTO;
import com.shopease.ecommerce.payload.CategoryResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {
    CategoryResponse findAll(int pageNumber, int pageSize);
    CategoryDTO findByCategoryId(Long id);

    Category addCategory(Category category) throws MethodArgumentNotValidException;

    void deleteCategory(Long categoryId);

    boolean updateCategory(Long categoryId, Category category);
}
