package org.iot.patterns.mapper;

import org.iot.patterns.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper implements CsvMappable<User> {
    @Override
    public User fromCsvRecord(List<String> entityData) {
        return User.builder()
                .firstName(entityData.get(1))
                .lastName(entityData.get(2))
                .email(entityData.get(3))
                .build();
    }
}
