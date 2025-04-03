package org.iot.patterns.mapper;

import org.iot.patterns.entity.Subscription;
import org.iot.patterns.entity.enums.SubscriptionStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class SubscriptionMapper implements CsvMappable<Subscription> {
    @Override
    public Subscription fromCsvRecord(List<String> entityData) {
        return Subscription.builder()
                .startDate(LocalDate.parse(entityData.get(3)))
                .endDate(LocalDate.parse(entityData.get(4)))
                .status(SubscriptionStatus.valueOf(entityData.get(5)))
                .build();
    }
}
