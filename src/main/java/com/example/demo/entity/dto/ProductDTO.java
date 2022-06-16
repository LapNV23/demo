package com.example.demo.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String id;
    private String name;
    private String slug;
    private String description;
    private String detail;
    private  String thumbnails;
    private BigDecimal price;
    private String createdAt;
    private String updatedAt;
    private String status;

}
