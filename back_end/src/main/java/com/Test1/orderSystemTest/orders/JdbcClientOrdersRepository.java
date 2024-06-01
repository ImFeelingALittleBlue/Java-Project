package com.Test1.orderSystemTest.orders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcClientOrdersRepository {

    private static final Logger log = LoggerFactory.getLogger(JdbcClientOrdersRepository.class);
    private final JdbcClient jdbcClient;

    public JdbcClientOrdersRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    public List<Orders> findAll(){
        return jdbcClient.sql("SELECT * FROM orders")
                .query(Orders.class)
                .list();
    }

    public Optional<Orders> findById(Integer id) {
        return jdbcClient.sql("SELECT id, order_time, customer_name, customer_number, total_price, status FROM orders WHERE id = :id")
                .param("id", id)
                .query(Orders.class)
                .optional();
    }

    public boolean existsById(Integer id) {
        int count = jdbcClient.sql("SELECT * FROM orders").query().listOfRows().size();
        return count > 0;
    }


    public void create(Orders orders) {
        var updated = jdbcClient.sql("INSERT INTO orders(order_time, customer_name, customer_number, total_price, status) VALUES(?, ?, ?, ?, ?)")
                .params(List.of(orders.getOrderTime(), orders.getCustomerName(), orders.getCustomerNumber(), orders.getTotalPrice(), orders.getStatus()))
                .update();

        Assert.state(updated == 1, "Failed to create order " + orders.getCustomerName());
    }

    public void update(Orders orders, Integer id) {
        var updated = jdbcClient.sql("UPDATE orders SET order_time = ?, customer_name = ?, customer_number = ?, total_price = ?, status = ? WHERE id = ?")
                .params(List.of(orders.getOrderTime(), orders.getCustomerName(), orders.getCustomerNumber(), orders.getTotalPrice(), orders.getStatus(), id))
                .update();

        Assert.state(updated == 1, "Failed to update order " + orders.getCustomerName());
    }

    public void delete(Integer id) {
        var updated = jdbcClient.sql("DELETE FROM orders WHERE id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete order " + id);
    }

    public int count() {
        return jdbcClient.sql("SELECT * FROM orders").query().listOfRows().size();
    }

    public Orders save(Orders orders) {
        if (orders.getId() == null) {
            create(orders);
        } else {
            update(orders, orders.getId());
        }
        return orders;
    }


    public void saveAll(List<Orders> orders) {
        orders.forEach(this::create);
    }

    public List<Orders> findByCustomerName(String customerName) {
        return jdbcClient.sql("SELECT * FROM orders WHERE customer_name = :customerName")
                .param("customerName", customerName)
                .query(Orders.class)
                .list();
    }
}
