package com.shopease.ecommerce.Repository;

import com.shopease.ecommerce.Model.Category;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    @Override
    @NonNull
    List<Category> findAll();
    @Override
    @NonNull
    Optional<Category> findById(@NonNull Long id);
    @NonNull
    Optional<Category> findByCategoryName(String categoryName);
}
