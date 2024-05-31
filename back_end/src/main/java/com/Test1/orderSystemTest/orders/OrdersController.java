package com.Test1.orderSystemTest.orders;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.Instant;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    private final OrdersRepository ordersRepository;
    private final OrderItemsRepository orderItemsRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);

    @PostMapping("/confirm")
    public String confirmOrder(@RequestBody OrderData orderData) {
        logger.info("Received order confirmation request: {}", orderData);
        // 在此处理订单逻辑
        // 创建并保存订单
        Orders order = new Orders();
        order.setOrderTime(Timestamp.from(Instant.now()).toLocalDateTime());
        order.setCustomerName("Customer Name"); // 这里可以根据需求设置实际的客户名称
        order.setCustomerNumber("Customer Number"); // 这里可以根据需求设置实际的客户号码
        order.setTotalPrice(orderData.getTotalPrice().doubleValue());
        order.setStatus("Pending"); // 设置初始订单状态

        Orders savedOrder = ordersRepository.save(order);

        // 创建并保存订单项
        List<String> items = orderData.getItems();
        for (String item : items) {
            OrderItems orderItem = new OrderItems();
            orderItem.setOrderId(savedOrder.getId());
            orderItem.setMenuItemId(Integer.parseInt(item)); // 假设 item 是 menu_item_id
            orderItem.setQuantity(1); // 设置数量，这里假设每项商品数量为1

            orderItemsRepository.save(orderItem);
        }

        return "Order confirmed successfully";
    }

    public OrdersController(OrdersRepository ordersRepository, OrderItemsRepository orderItemsRepository) {
        this.ordersRepository = ordersRepository;
        this.orderItemsRepository = orderItemsRepository;
    }

    @GetMapping("")
    public List<Orders> findAll(){
        return StreamSupport.stream(ordersRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Orders findById(@PathVariable Integer id){
        return ordersRepository.findById(id)
                .orElseThrow(OrdersNotFoundException::new);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Orders create(@RequestBody Orders orders){
        return ordersRepository.save(orders);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Orders orders, @PathVariable Integer id){
        if (!ordersRepository.existsById(id)) {
            throw new OrdersNotFoundException();
        }
        ordersRepository.save(orders);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        if (!ordersRepository.existsById(id)) {
            throw new OrdersNotFoundException();
        }
        ordersRepository.deleteById(id);
    }
}
