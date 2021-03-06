package com.example.demo.entity;

import com.example.demo.entity.base.BaseEntity;
import com.example.demo.entity.enums.OrderSimpleStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    private String id;
    private String userId; // hien tai tu nhap
    private BigDecimal totalPrice;
    @Enumerated(EnumType.ORDINAL)// anhs xaj status cua orders tu base ve
    private OrderSimpleStatus status;//

    @OneToMany(mappedBy =  "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<OrderDetail> orderDetails;

    @PostPersist
    public void updateSlug(){
        System.out.println("Before save");
        System.out.println(id);
    }

    @PostUpdate
    public void afterSave(){
        System.out.println("After save");
        System.out.println(id);
    }

//    public void addTotalPrice(OrderDetail orderDetail){
//        if (this.totalPrice == null){
//            this.totalPrice = new BigDecimal(0);
//        }
//        BigDecimal quantityInBigDecimal = new BigDecimal(orderDetail.getQuantity);
//        this.totalPrice = this.totalPrice.add(
//                orderDetail.getUnitPrice().multiply(quantityInBigDecimal));
//
//    }
}
