package com.egoravdeev.courseworkbackend.service;

import com.egoravdeev.courseworkbackend.dao.OrderDAO;
import com.egoravdeev.courseworkbackend.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public List<Order> getOrders() {
        return orderDAO.findAll();
    }

    public void addOrder(String orderDate, int status, String deliveryDate, String vehicle) {
        orderDAO.addOrder(new Order(0, orderDate, status, deliveryDate, vehicle));
    }
}
