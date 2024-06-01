package com.Test1.orderSystemTest.orders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcClientOrderItemsRepository {

    private static final Logger log = LoggerFactory.getLogger(JdbcClientOrderItemsRepository.class);
    private final JdbcClient jdbcClient;

    public JdbcClientOrderItemsRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<OrderItems> findAll() {
        return jdbcClient.sql("SELECT * FROM order_items")
                .query(OrderItems.class)
                .list();
    }

    public Optional<OrderItems> findById(Integer id) {
        return jdbcClient.sql("SELECT id, order_id, menu_item_id, quantity FROM order_items WHERE id = :id")
                .param("id", id)
                .query(OrderItems.class)
                .optional();
    }

    public boolean existsById(Integer id) {
        int count = jdbcClient.sql("SELECT * FROM order_items WHERE id = :id")
                            .param("id", id)
                            .query()
                            .listOfRows()
                            .size();
        return count > 0;
    }

    public void create(OrderItems orderItems) {
        var updated = jdbcClient.sql("INSERT INTO order_items(order_id, menu_item_id, quantity) VALUES (?, ?, ?)")
                .params(List.of(orderItems.getOrderId(), orderItems.getMenuItemId(), orderItems.getQuantity()))
                .update();

        Assert.state(updated == 1, "Failed to create order item");
    }

    public void update(OrderItems orderItems, Integer id) {
        var updated = jdbcClient.sql("UPDATE order_items SET order_id = ?, menu_item_id = ?, quantity = ? WHERE id = ?")
                .params(List.of(orderItems.getOrderId(), orderItems.getMenuItemId(), orderItems.getQuantity(), id))
                .update();

        Assert.state(updated == 1, "Failed to update order item");
    }

    public void delete(Integer id) {
        var updated = jdbcClient.sql("DELETE FROM order_items WHERE id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete order item");
    }

    public int count() {
        return jdbcClient.sql("SELECT * FROM order_items").query().listOfRows().size();
    }

    public OrderItems save(OrderItems orderItems) {
        if (orderItems.getId() == null) {
            create(orderItems);
        } else {
            update(orderItems, orderItems.getId());
        }
        return orderItems;
    }

    public void saveAll(List<OrderItems> orderItemsList) {
        orderItemsList.forEach(this::create);
    }

    // You can add additional methods as needed

}
