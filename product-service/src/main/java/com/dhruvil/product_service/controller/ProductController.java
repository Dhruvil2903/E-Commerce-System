package com.dhruvil.product_service.controller;

import com.dhruvil.product_service.customException.ProductIsAlreadyExistException;
import com.dhruvil.product_service.customException.ProductNotFoundException;
import com.dhruvil.product_service.dto.ProductRequest;
import com.dhruvil.product_service.dto.ProductResponse;
import com.dhruvil.product_service.serviceImpl.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService){
        this.productService = productService;
    }

    @GetMapping("/{productName}")
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<ProductResponse> productResponse = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }

    @PostMapping("/createProduct")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) throws ProductIsAlreadyExistException, ProductNotFoundException {
        ProductResponse productResponse = productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    @PutMapping("/{productName}")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest) throws ProductNotFoundException {
        ProductResponse productResponse = productService.updateProduct(productRequest);
        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }

    @DeleteMapping("/{productName}")
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable String productName) throws ProductNotFoundException {

        ProductRequest request = new ProductRequest(productName);

        ProductResponse productResponse = productService.deleteProduct(request);
        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }
}
