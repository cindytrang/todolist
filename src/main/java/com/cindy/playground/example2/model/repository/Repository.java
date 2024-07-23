package com.cindy.playground.example2.model.repository;

import java.util.List;
import java.util.UUID;

// Strategy
public interface Repository<T, ID> {
    void save(T obj);
    List<T> findAll();
    T findById(ID id);
    T update(T obj);
    T delete(T obj);
}
