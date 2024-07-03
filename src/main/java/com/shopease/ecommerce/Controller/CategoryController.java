package com.shopease.ecommerce.Controller;

import com.shopease.ecommerce.Model.Category;
import com.shopease.ecommerce.Service.CategoryService;
import com.shopease.ecommerce.payload.CategoryDTO;
import com.shopease.ecommerce.payload.CategoryResponse;
import com.sun.net.httpserver.Authenticator;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/api/public/categories")
    public CategoryResponse findAllCategory(
            @RequestParam(name = "pagenumber") Integer pageNumber,
            @RequestParam(name = "pagesize") Integer pageSize
    ){

        return categoryService.findAll(pageNumber,pageSize);
    }

    @GetMapping("/api/public/{categoryId}")
    public ResponseEntity<?> findByCategoryId(@PathVariable("categoryId") Long categoryId) {
        CategoryDTO category = categoryService.findByCategoryId(categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
    @PostMapping("api/admin/add/category")
    public ResponseEntity<?> createCategory(@RequestBody @Valid Category category) throws MethodArgumentNotValidException {
        Category returnedCategory = categoryService.addCategory(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("api/admin/category/update/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody Category category){
        if(categoryService.updateCategory(categoryId, category)){
            return new ResponseEntity<>("Category Successfully Updated",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Not Found",HttpStatusCode.valueOf(404));
        }
    }
}
