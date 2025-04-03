package org.iot.patterns.mapper;

import org.iot.patterns.entity.Payment;
import org.iot.patterns.entity.enums.PaymentMethod;
import org.iot.patterns.entity.enums.PaymentStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Component
public class PaymentMapper implements CsvMappable<Payment> {
    @Override
    public Payment fromCsvRecord(List<String> entityData) {
        return Payment.builder()
                .amount(new BigDecimal(entityData.get(2)))
                .createdAt(Timestamp.valueOf(entityData.get(3)))
                .paymentMethod(PaymentMethod.valueOf(entityData.get(4)))
                .status(PaymentStatus.valueOf(entityData.get(5)))
                .build();
    }
}
