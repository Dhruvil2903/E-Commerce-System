package com.dhruvil.product_service.service;

import com.dhruvil.product_service.customException.ProductIsAlreadyExistException;
import com.dhruvil.product_service.customException.ProductNotFoundException;
import com.dhruvil.product_service.dto.ProductRequest;
import com.dhruvil.product_service.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getAllProducts();
    ProductResponse createProduct(ProductRequest productRequest) throws ProductNotFoundException, ProductIsAlreadyExistException;
    ProductResponse updateProduct(ProductRequest productRequest) throws ProductNotFoundException;
    ProductResponse deleteProduct(ProductRequest productRequest) throws ProductNotFoundException;
}
