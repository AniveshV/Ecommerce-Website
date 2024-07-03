package com.shopease.ecommerce.Service.Implementation;

import com.shopease.ecommerce.Exception.ResourceNotFoundException;
import com.shopease.ecommerce.Model.Category;
import com.shopease.ecommerce.Model.Product;
import com.shopease.ecommerce.Repository.CategoryRepository;
import com.shopease.ecommerce.Repository.ProductRepository;
import com.shopease.ecommerce.Service.ProductService;
import com.shopease.ecommerce.payload.CategoryDTO;
import com.shopease.ecommerce.payload.ProductDTO;
import com.shopease.ecommerce.payload.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDTO productDTO;
    @Autowired
    ProductResponse productResponse;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public ProductResponse findAll(int pageNumber, int pageSize) {
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize);
        Page<Product> productPage = productRepository.findAll(pageDetails);
        List<Product> products = productPage.getContent();
        List<ProductDTO> productDTOList = products.stream().map(product -> new ProductDTO(product.getProductId(),product.getProductName(),product.getDescription(), product.getQuantity(),product.getPrice(),product.getCategory())).toList();
        productResponse.setProductDTOList(productDTOList);
        productResponse.setPageNumber(productPage.getNumber());
        productResponse.setPageSize(productPage.getSize());
        productResponse.setTotalPages(productPage.getTotalPages());
        productResponse.setTotalElements(productPage.getTotalElements());
        productResponse.setLastPage(productPage.isLast());

        return productResponse;
    }

    @Override
    public ProductDTO findByProductId(Long id) {
        return null;
    }


    @Override
    public ProductDTO addProduct(Product product, Long categoryId) throws MethodArgumentNotValidException {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isPresent()){
            product.setCategory(category.get());
            Product savedProduct = productRepository.save(product);
            productDTO.setProductName(product.getProductName());
            productDTO.setProductId(product.getProductId());
            productDTO.setDescription(product.getDescription());
            productDTO.setPrice(product.getPrice());
            productDTO.setQuantity(product.getQuantity());
            productDTO.setCategory(category.get());

            return productDTO;
        }
        else{
            throw new ResourceNotFoundException("Category Not found");
        }
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public boolean updateProduct(Long productId, Long categoryId, Product product) {
        Product existingProduct = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product Not Found"));
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category Not Found"));
        existingProduct.setProductName(product.getProductName());
        existingProduct.setCategory(category);
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        productRepository.save(existingProduct);
        return true;
    }

//    @Override
//    public ProductResponse getAllProducts() {
//        List<Product> productList = productRepository.findAll();
//        if (!productList.isEmpty()) {
//            List<ProductDTO> productDTOList = productList.stream().map(product -> new ProductDTO(product.getProductId(), product.getProductName(), product.getDescription(),
//                    product.getQuantity(), product.getPrice(), product.getCategory())).toList();
//            productResponse.setProductDTOList(productDTOList);
//        }
//        return productResponse;
//    }

    @Override
    public ProductResponse getProductsByCategory(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        List<Product> products = productRepository.findByCategory(category.get());
        if(!products.isEmpty()) {
            List<ProductDTO> productDTOList = products.stream().map(product -> new ProductDTO(product.getProductId(), product.getProductName(), product.getDescription(),
                    product.getQuantity(), product.getPrice(), product.getCategory())).toList();
            productResponse.setProductDTOList(productDTOList);
        }
        return productResponse;
    }


}
