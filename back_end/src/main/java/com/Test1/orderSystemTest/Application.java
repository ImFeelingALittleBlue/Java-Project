package com.Test1.orderSystemTest;

import com.Test1.orderSystemTest.orders.Orders;
import com.Test1.orderSystemTest.orders.OrdersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner runner(OrdersRepository ordersRepository) {
        return args -> {
            List<Orders> ordersList = Arrays.asList(
                new Orders(1, LocalDateTime.now(), "Eric", "0966109152", 100.0, "note"),
                new Orders(2, LocalDateTime.now(), "John", "0912345678", 200.0, "note"),
                new Orders(3, LocalDateTime.now(), "Alice", "0922334455", 300.0, "note"),
                new Orders(4, LocalDateTime.now(), "Bob", "0933445566", 400.0, "note"),
                new Orders(5, LocalDateTime.now(), "Charlie", "0944556677", 500.0, "note"),
                new Orders(6, LocalDateTime.now(), "David", "0955667788", 600.0, "note"),
                new Orders(7, LocalDateTime.now(), "Eve", "0966778899", 700.0, "note"),
                new Orders(8, LocalDateTime.now(), "Frank", "0977889900", 800.0, "note"),
                new Orders(9, LocalDateTime.now(), "Grace", "0988990011", 900.0, "note"),
                new Orders(10, LocalDateTime.now(), "Hannah", "0999001122", 1000.0, "note")
            );
            ordersRepository.saveAll(ordersList);
            log.info("Orders have been inserted into the database.");
        };
    }
}
