package com.dhruvil.product_service.serviceImpl;

import com.dhruvil.product_service.customException.ProductIsAlreadyExistException;
import com.dhruvil.product_service.customException.ProductNotFoundException;
import com.dhruvil.product_service.dto.ProductRequest;
import com.dhruvil.product_service.dto.ProductResponse;
import com.dhruvil.product_service.entity.Product;
import com.dhruvil.product_service.repository.ProductRepository;
import com.dhruvil.product_service.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll().stream().map(product -> new ProductResponse(product.getId(), product.getProductName(), product.getProductPrice(), product.getDescription(), product.getSku(), product.getStockQuantity(), product.getCategory(), product.getBrand()))
                .toList();
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) throws ProductNotFoundException, ProductIsAlreadyExistException {

        Optional<Product> productExist = productRepository.findByProductName(productRequest.getProductName());

        if (productExist.isPresent()) {
            throw new ProductIsAlreadyExistException("Can't create new product because product is already created");
        }

        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setProductPrice(productRequest.getProductPrice());
        product.setSku(productRequest.getSku());
        product.setDescription(productRequest.getDescription());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setCategory(productRequest.getCategory());
        product.setBrand(productRequest.getBrand());

        productRepository.save(product);

        return new ProductResponse(product.getId(), product.getProductName(), product.getProductPrice(), product.getDescription(), product.getSku(), product.getStockQuantity(), product.getCategory(), product.getBrand());
    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest) throws ProductNotFoundException {

        Optional<Product> productExist = productRepository.findByProductName(productRequest.getProductName());

        if(productExist.isEmpty()){
            throw new ProductNotFoundException("Product is not found for update");
        }

        Product product = productExist.get();
        product.setProductName(productRequest.getProductName());
        product.setProductPrice(productRequest.getProductPrice());
        product.setSku(productRequest.getSku());
        product.setDescription(productRequest.getDescription());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setCategory(productRequest.getCategory());
        product.setBrand(productRequest.getBrand());
        return new ProductResponse(product.getId(), product.getProductName(), product.getProductPrice(), product.getDescription(), product.getSku(), product.getStockQuantity(), product.getCategory(), product.getBrand());
    }

    @Override
    public ProductResponse deleteProduct(ProductRequest productRequest) throws ProductNotFoundException {

        Optional<Product> productExists = productRepository.findByProductName(productRequest.getProductName());

        if(productExists.isEmpty()){
            throw new ProductNotFoundException("Product is not found");
        }

        Product product = productExists.get();
        productRepository.delete(product);
        return new ProductResponse(product.getId(), product.getProductName(), product.getProductPrice(), product.getDescription(), product.getSku(), product.getStockQuantity(), product.getCategory(), product.getBrand());
    }
}
