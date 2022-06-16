package com.example.demo.entity;

import com.example.demo.entity.base.BaseEntity;
import com.example.demo.entity.enums.ProductStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Id
    private String id;
    private String name;
    private String slug;
    private String description;
    private String detail;
    private String thumbnails;//
    private BigDecimal price;
    @Enumerated(EnumType.ORDINAL)// ánh xạ giá trị của status
    private ProductStatus status;

    public Product(){
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }

//    Cách cơ bản để private stastus
//    private int status;
//    @Transient
//    private ProductStatus productStatus;
//
//    @PostLoad // khi load
//    void fillTransient() {
//        this.productStatus = ProductStatus.of(status); // chuyển dữ liệu dạng số ở trong database về kiểu enum
//    }
//
//    @PrePersist // trước khi lưu
//    void fillPersistent() {
//        // đưa giá trị của product status vào trường status.
//        this.status = this.productStatus.getValue();
//    }
}


