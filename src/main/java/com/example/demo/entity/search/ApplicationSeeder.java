package com.example.demo.entity.search;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.OrderDetailId;
import com.example.demo.entity.Product;
import com.example.demo.entity.enums.OrderSimpleStatus;
import com.example.demo.entity.enums.ProductSimpleStatus;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.util.StringHelper;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class ApplicationSeeder implements CommandLineRunner {
    boolean createSeeData = false;
    final OrderRepository orderRepository;
    final ProductRepository productRepository;
    Faker faker;
    int numberOfProduct = 200;
    int numberOfOrder = 10000;

    public ApplicationSeeder(
            OrderRepository orderRepository,
            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.faker = new Faker();
    }

    @Override
    public void run(String... args) throws Exception{
        if (createSeeData){
            //reset data
            orderRepository.deleteAll();
            productRepository.deleteAll();
            seedProduct();
            seedOrder();
        }
    }

    private void seedOrder(){
        List<Product> product = productRepository.findAll();
        //sinh ra list order de save
        List<Order> orders = new ArrayList<>();
        for (int i = 0;i<numberOfOrder; i++){
            //information a order
            Order order = new Order();
            order.setUserId("0");;///tamj thoi de mac dinh
            order.setId(UUID.randomUUID().toString());
            //Generate order details
            Set<OrderDetail> orderDetails = new HashSet<>();
            //sinh ra random soos luowngj order detail cho 1 don hang
            int randomOrderDetailNumber = faker.number().numberBetween(1, 5);
            HashSet<String> existingProductId = new HashSet<>();
            for (int j =0;j<randomOrderDetailNumber;j++){
                //generate 1 item
                OrderDetail orderDetail = new OrderDetail();
                //laasu ra product tu list
                Product randomProduct = product.get(
                        faker.number().numberBetween(0,product.size() - 1));
                //cau lenh dk de tranh tinh tranbg san pham trungf nhau
                if (existingProductId.contains(randomProduct.getId())){
                    continue;//bo qua neu trung or random lai product khasc
                }
                existingProductId.add(randomProduct.getId());
                //taoj khoa chinh tu order va product id
                orderDetail.setId(new OrderDetailId(order.getId(),randomProduct.getId()));
                //set quan he voi order
                orderDetail.setOrder(order);
                //set quan he voi product
                orderDetail.setProduct(randomProduct);
                orderDetail.setUnitPrice(randomProduct.getPrice());
                orderDetail.setQuantity(faker.number().numberBetween(1,5));
                //total price

                //save vao list order detail
                orderDetails.add(orderDetail);
            }
            //set orderDetails vafo order
            order.setOrderDetails(orderDetails);
            order.setStatus(OrderSimpleStatus.DONE);
            //add order vaof list orders ben ngoai, de co the save all
            orders.add(order);
        }
        orderRepository.saveAll(orders);
    }

    private void seedProduct(){
        List<Product> listProduct = new ArrayList<>();
        for (int i = 0;i<numberOfProduct;i++){
            System.out.println(i + 1);
            Product product = new Product();
            product.setId(UUID.randomUUID().toString());
            product.setName(faker.name().title());
            product.setSlug(StringHelper.toSlug(product.getName()));
            product.setDescription(faker.lorem().sentence());//text
            product.setPrice(
                    new BigDecimal(faker.number().numberBetween(100, 200) * 10000)
            );
            product.setCreatedBy("0");
            product.setUpdatedBy("0");
            product.setDetail(faker.lorem().sentence());
            product.setThumbnails(faker.avatar().image());
            product.setStatus(ProductSimpleStatus.ACTIVE);
            listProduct.add(product);
            System.out.println(product.toString());
        }
        productRepository.saveAll(listProduct);
    }
    public static void main(String[] args){

    }
}
