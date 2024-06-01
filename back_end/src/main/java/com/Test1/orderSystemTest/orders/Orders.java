package com.Test1.orderSystemTest.orders;

import javax.persistence.*;
import java.time.LocalDateTime;
@org.springframework.boot.autoconfigure.domain.EntityScan("com.*")
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_time")
    private LocalDateTime orderTime;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_number")
    private String customerNumber;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "status")
    private String status;

    public Orders() {
        // Default constructor
    }

    public Orders(Integer id, LocalDateTime orderTime, String customerName, String customerNumber, Double totalPrice, String status) {
        if (orderTime.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Order time must be before present time.");
        }
        if (customerName == null || customerName.isEmpty()) {
            throw new IllegalArgumentException("Customer name must not be empty.");
        }
        if (customerNumber == null || customerNumber.isEmpty()) {
            throw new IllegalArgumentException("Customer phone number must not be empty.");
        }
        if (totalPrice == null || totalPrice < 0) {
            throw new IllegalArgumentException("Total price must be non-negative.");
        }
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Order status must not be empty.");
        }

        this.id = id;
        this.orderTime = orderTime;
        this.customerName = customerName;
        this.customerNumber = customerNumber;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        if (orderTime.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Order time must be before present time.");
        }
        this.orderTime = orderTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        if (customerName == null || customerName.isEmpty()) {
            throw new IllegalArgumentException("Customer name must not be empty.");
        }
        this.customerName = customerName;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        if (customerNumber == null || customerNumber.isEmpty()) {
            throw new IllegalArgumentException("Customer phone number must not be empty.");
        }
        this.customerNumber = customerNumber;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        if (totalPrice == null || totalPrice < 0) {
            throw new IllegalArgumentException("Total price must be non-negative.");
        }
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Order status must not be empty.");
        }
        this.status = status;
    }
}
