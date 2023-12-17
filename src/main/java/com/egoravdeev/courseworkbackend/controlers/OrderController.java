package com.egoravdeev.courseworkbackend.controlers;

import com.egoravdeev.courseworkbackend.model.Order;
import com.egoravdeev.courseworkbackend.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@CrossOrigin(origins = "*")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @PostMapping("/orders")
    public void addOrder(@RequestParam String orderDate, @RequestParam int status, @RequestParam String deliveryDate, @RequestParam String vehicle) {
        orderService.addOrder(orderDate, status, deliveryDate, vehicle);
    }
}
