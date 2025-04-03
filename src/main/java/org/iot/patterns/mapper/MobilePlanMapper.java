package org.iot.patterns.mapper;

import org.iot.patterns.entity.MobilePlan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MobilePlanMapper implements CsvMappable<MobilePlan> {
    @Override
    public MobilePlan fromCsvRecord(List<String> entityData) {
        return MobilePlan.builder()
                .name(entityData.get(1))
                .description(entityData.get(2))
                .price(Double.parseDouble(entityData.get(3)))
                .dataLimit(Integer.parseInt(entityData.get(4)))
                .callMinutes(Integer.parseInt(entityData.get(5)))
                .smsCount(Integer.parseInt(entityData.get(6)))
                .build();
    }
}
