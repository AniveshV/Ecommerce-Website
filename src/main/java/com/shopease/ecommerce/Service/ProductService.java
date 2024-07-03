package com.shopease.ecommerce.Service;

import com.shopease.ecommerce.Model.Product;
import com.shopease.ecommerce.payload.ProductDTO;
import com.shopease.ecommerce.payload.ProductResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
public interface ProductService {
    ProductResponse findAll(int pageNumber, int pageSize);
    ProductDTO findByProductId(Long id);



    ProductDTO addProduct(Product product, Long categoryId) throws MethodArgumentNotValidException;

    void deleteProduct(Long productId);

    boolean updateProduct(Long productId, Long categoryId, Product product);

    //ProductResponse getAllProducts();

    ProductResponse getProductsByCategory(Long categoryId);
}
