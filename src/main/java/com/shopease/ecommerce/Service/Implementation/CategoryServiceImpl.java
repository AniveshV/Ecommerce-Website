package com.shopease.ecommerce.Service.Implementation;

import com.shopease.ecommerce.Exception.ResourceExistsException;
import com.shopease.ecommerce.Exception.ResourceNotFoundException;
import com.shopease.ecommerce.Model.Category;
import com.shopease.ecommerce.Repository.CategoryRepository;
import com.shopease.ecommerce.Service.CategoryService;
import com.shopease.ecommerce.payload.CategoryDTO;
import com.shopease.ecommerce.payload.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryResponse categoryResponse;
    @Override
    public CategoryResponse findAll(int pageNumber, int pageSize) {

        Pageable pageDetails = PageRequest.of(pageNumber,pageSize);
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);
        List<Category> categories = categoryPage.getContent();
        //List<Category> categories = categoryRepository.findAll();
         List<CategoryDTO> categoryDTOList = categories.stream().map(category -> new CategoryDTO(category.getCategoryId(),category.getCategoryName())).toList();
         categoryResponse.setCategoryDTOList(categoryDTOList);
         categoryResponse.setPageNumber(categoryPage.getNumber());
         categoryResponse.setPageSize(categoryPage.getSize());
         categoryResponse.setTotalPages(categoryPage.getTotalPages());
         categoryResponse.setTotalElements(categoryPage.getTotalElements());
         categoryResponse.setLastPage(categoryPage.isLast());

         return categoryResponse;
    }
    @Override
    public CategoryDTO findByCategoryId(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            return new CategoryDTO(category.get().getCategoryId(), category.get().getCategoryName());
        }
        else{
            throw new ResourceNotFoundException("Resource Not Found");
        }
    }

    @Override
    public Category addCategory(Category category) throws MethodArgumentNotValidException {
        Optional<Category> existingCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(existingCategory.isPresent()){
            throw new ResourceExistsException("Category Already Exists!");
        }
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public boolean updateCategory(Long categoryId, Category category) {
        Optional<Category> existingCategory = categoryRepository.findById(categoryId);
        if(existingCategory.isPresent()){
           existingCategory.get().setCategoryName(category.getCategoryName());
           categoryRepository.save(existingCategory.get());
           //SELECT * FROM category WHERE id = ?;
            //UPDATE category SET category_name = ? WHERE id = ?;
            return true;
        }
        return false;
    }
}
