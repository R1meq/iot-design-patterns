package org.iot.patterns.mapper;

import java.util.List;

public interface CsvMappable<T> {
    T fromCsvRecord(List<String> entityData);

    default List<T> fromCsvRecords(List<List<String>> entityData) {
        return entityData.stream().map(this::fromCsvRecord).toList();
    }
}
