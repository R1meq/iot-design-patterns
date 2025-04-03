package org.iot.patterns.mapper;

import org.iot.patterns.entity.Order;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class OrderMapper implements CsvMappable<Order> {
    @Override
    public Order fromCsvRecord(List<String> entityData) {
        return Order.builder()
                .createdAt(Timestamp.valueOf(entityData.get(3)))
                .build();
    }
}
