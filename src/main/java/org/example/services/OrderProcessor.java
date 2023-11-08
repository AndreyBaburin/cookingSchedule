package org.example.services;

import jakarta.transaction.Transactional;
import org.example.data.OrderJdbcDao;
import org.example.domain.Order;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderProcessor {
    private final OrderJdbcDao orderJdbcDao;

    public OrderProcessor(OrderJdbcDao orderJdbcDao) {
        this.orderJdbcDao = orderJdbcDao;
    }

    public List<Order> getAllOrders() {
        return orderJdbcDao.getAllOrders();
    }

    public Order getOrderById(int id) {
        Order order = orderJdbcDao.getOrderById(id);
        if (order == null) {
            System.out.println("No such order in DB for given ID");
        }
        return order;
    }

    public void createOrder(Order o) {
//        o.setDateStartCooking(new Date());
        o.setDateStartCooking(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm")));
        o.setOrderDone(LocalDateTime.now().plusMinutes(o.getTimeCooking()).
                format(DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm")));
        orderJdbcDao.insertOrder(o);
    }

    public void updateOrder(int id, Order o) {
        o.setId(id);
        orderJdbcDao.updateOrder(o);
    }

    public void deleteById(int id) {
        orderJdbcDao.deleteOrderById(id);
    }
}
