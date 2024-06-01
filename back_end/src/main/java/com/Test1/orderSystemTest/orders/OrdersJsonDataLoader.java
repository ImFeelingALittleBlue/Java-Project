package com.Test1.orderSystemTest.orders;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class OrdersJsonDataLoader{

    private static final Logger log = LoggerFactory.getLogger(OrdersJsonDataLoader.class);
    private final JdbcClientOrdersRepository JdbcClientOrdersRepository;
    private final ObjectMapper objectMapper;
    public OrdersJsonDataLoader(JdbcClientOrdersRepository JdbcClientOrdersRepository, ObjectMapper objectMapper){
        this.JdbcClientOrdersRepository = JdbcClientOrdersRepository;
        this.objectMapper = objectMapper;
    }

//    @Override
//    public void run(String... args) throws Exception {
//        if(ordersRepository.count() == 0) {
//            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/orders.json")) {
//                Orderz allRuns = objectMapper.readValue(inputStream, Orderz.class);
//                log.info("Reading {} runs from JSON data and saving to database.", allRuns.orderz().size());
//                ordersRepository.saveAll(allRuns.orderz());
//            } catch (IOException e) {
//                throw new RuntimeException("Failed to read JSON data", e);
//            }
//        } else {
//            log.info("Not loading Runs from JSON data because the collection contains data.");
//        }
//    }
}
