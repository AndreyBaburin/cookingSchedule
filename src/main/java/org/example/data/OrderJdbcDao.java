package org.example.data;

import org.example.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.security.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public class OrderJdbcDao {
    private final Logger log = LoggerFactory.getLogger(OrderJdbcDao.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Order> getAllOrders() {
        log.info("Running Spring JDBC query to SELECT *");
        return jdbcTemplate.query(
                "select * from orders",
                new BeanPropertyRowMapper<>(Order.class));
    }

    public Order getOrderById(int id) {
        log.info("Running Spring JDBC query to SELECT one order");
        Order order;
        try {
            order = jdbcTemplate.queryForObject(
                    "SELECT * FROM orders WHERE ID = ?",
                    new BeanPropertyRowMapper<>(Order.class),
                    id);
        } catch (EmptyResultDataAccessException e) {
            order = null;
            System.out.println(e.getMessage());
            System.out.println("No such order in DB for given ID");
        }
        return order;
    }

    public void insertOrder(Order order_name) {
        log.info("Running Spring JDBC query to INSERT");
        String sql = "insert into orders (order_name, timeCooking,dateStartCooking,orderDone) values (?,?,?,?)";
        jdbcTemplate.update(sql, order_name.getOrder_name(), order_name.getTimeCooking(),
                order_name.getDateStartCooking(), order_name.getOrderDone());
    }

    public int updateOrder(Order order_name) {
        log.info("Running Spring JDBC query to UPDATE");
        String sql = "update orders set order_name=? where id=?";
        return jdbcTemplate.update(sql, order_name.getOrder_name(), order_name.getId());
    }


    public void deleteOrderById(int id) {
        log.info("Running Spring JDBC query to DELETE");
        String sql = "delete from orders where id=?";
        jdbcTemplate.update(sql, id);
    }
}
