package com.example.reservationsystem.dao;

public interface Dao<T> {
    T getById(String id);
    void insert(T entity);
    void updateById(String id, T entity);
    void deleteById(String id);
}
