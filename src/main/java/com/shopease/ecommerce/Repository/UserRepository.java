package com.shopease.ecommerce.Repository;

import com.shopease.ecommerce.Model.Category;
import com.shopease.ecommerce.Model.Product;
import com.shopease.ecommerce.Model.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    @NonNull
    List<User> findAll();
    @Override
    @NonNull
    Optional<User> findById(@NonNull Long id);

    @NonNull
    Optional<User> findByUsername(@NonNull String username);
}
