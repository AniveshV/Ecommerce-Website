package com.shopease.ecommerce.Repository;

import com.shopease.ecommerce.Model.Category;
import com.shopease.ecommerce.Model.Product;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    @NonNull
    List<Product> findAll();
    @Override
    @NonNull
    Optional<Product> findById(@NonNull Long id);

    List<Product> findByCategory(Category category);
}
