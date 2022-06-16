package com.example.demo.entity;

import com.example.demo.entity.enums.ProductStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test
    public void checkProductStatus(){
        System.out.println(ProductStatus.of(-1));
    }
}
