package org.iot.patterns.service;

import java.util.List;

public interface GeneralService<T> {
    void writeToDatabase();
    List<T> findAll();
    T findById(Long id);
    T save(T entity);
    void update(Long id, T entity);
    void delete(Long id);
}
