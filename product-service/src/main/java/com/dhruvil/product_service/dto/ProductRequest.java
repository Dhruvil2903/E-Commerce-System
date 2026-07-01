package com.dhruvil.product_service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
public class ProductRequest {

    private Long id;
    private String productName;
    private BigDecimal productPrice;
    private String description;
    private String sku;
    private Integer stockQuantity;
    private String category;
    private String brand;

    public ProductRequest(String ProductName){
        this.productName = ProductName;
    }
}
