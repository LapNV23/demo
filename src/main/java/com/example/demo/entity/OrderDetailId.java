package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable//khai báo nhúng order_id và product_id vào class OrderDetailId
public class OrderDetailId implements Serializable {
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "product_id")
    private String productId;
}
