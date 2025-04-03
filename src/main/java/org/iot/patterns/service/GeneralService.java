package org.iot.patterns.service;

import java.util.List;

public interface GeneralService<T> {
    void writeToDatabase();
    List<T> findAll();
}
