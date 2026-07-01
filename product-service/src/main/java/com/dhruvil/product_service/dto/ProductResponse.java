package com.dhruvil.product_service.dto;

import java.math.BigDecimal;

public record ProductResponse(Long id , String productName,
                              BigDecimal productPrice,
                              String description,
                              String sku,
                              Integer stockQuantity,
                              String category,
                              String brand) {

}
