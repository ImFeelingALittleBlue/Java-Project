package com.Test1.orderSystemTest.orders;
import java.math.BigDecimal;
import java.util.List;
import java.sql.Timestamp;

public class OrderData {

    private List<String> items;
    private BigDecimal totalPrice;
    private String notes;

    // Getters and setters

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
