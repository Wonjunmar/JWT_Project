package com.example.demo.orders.service;

import com.example.demo.member.model.Member;
import com.example.demo.orders.model.Orders;
import com.example.demo.orders.repository.OrdersRepository;
import com.example.demo.product.model.Product;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    OrdersRepository ordersRepository;

    public OrderService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public void create(Integer memberIdx, Integer productIdx) {

        ordersRepository.save(Orders.builder()
                .member(Member.builder().idx(memberIdx).build())
                .product(Product.builder().idx(productIdx).build())
                .build());
    }


    public void delete(Integer idx) {
        ordersRepository.delete(Orders.builder()
                .idx(idx).build());
    }
}
