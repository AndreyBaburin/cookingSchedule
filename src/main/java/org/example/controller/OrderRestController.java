package org.example.controller;

import org.example.data.OrderJdbcDao;
import org.example.domain.Order;
import org.example.services.OrderProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rest")
public class OrderRestController {
private final OrderProcessor orderProcessor;

    public OrderRestController(OrderProcessor orderProcessor) {
        this.orderProcessor = orderProcessor;
    }

    @GetMapping("/orders")
    public List<Order> getAllTasks() {
        return orderProcessor.getAllOrders();
    }

    @RequestMapping("/orders/{id}")
    public Order loadOneByPathVariable(@PathVariable int id) {
        return orderProcessor.getOrderById(id);
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNew(@RequestBody Order order_name) {
        orderProcessor.createOrder(order_name);
    }

    @PutMapping("/orders/{id}")
    public void update(@PathVariable int id, @RequestBody Order order) {
        orderProcessor.updateOrder(id, order);
    }

    @DeleteMapping("/orders/{id}")
    public void delete(@PathVariable int id) {
        orderProcessor.deleteById(id);
    }
}
