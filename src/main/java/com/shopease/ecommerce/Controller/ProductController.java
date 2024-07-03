package com.shopease.ecommerce.Controller;

import com.shopease.ecommerce.Model.Category;
import com.shopease.ecommerce.Model.Product;
import com.shopease.ecommerce.Repository.ProductRepository;
import com.shopease.ecommerce.Service.ProductService;
import com.shopease.ecommerce.payload.ProductDTO;
import com.shopease.ecommerce.payload.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("admin/product/{categoryId}/add")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody @Valid Product product, @PathVariable("categoryId") Long categoryId) throws MethodArgumentNotValidException {
        ProductDTO productDTO = productService.addProduct(product,categoryId);
        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }

    @GetMapping("user/products")
    public ResponseEntity<ProductResponse> getAllProducts(@RequestParam(name = "pagenumber") Integer pageNumber,
                                                          @RequestParam(name = "pagesize") Integer pageSize){
        ProductResponse productResponse = productService.findAll(pageNumber,pageSize);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }

    @GetMapping("user/{categoryId}/products")
    public ResponseEntity<ProductResponse> getProductsByCategory(@PathVariable Long categoryId){
        ProductResponse productResponse = productService.getProductsByCategory(categoryId);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }

    @PutMapping("admin/{product_id}/{category_id}/update/product")
    public ResponseEntity<?> updateProduct(@PathVariable(name = "product_id") Long productId,@PathVariable(name = "category_id") Long categoryId, @RequestBody Product product){
        productService.updateProduct(productId,categoryId, product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("admin/delete/{product_id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "product_id") Long productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
