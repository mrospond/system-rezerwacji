package com.example.reservationsystem.database.dao;

public interface Dao<I, T> {
    T getById(I id);
    void insert(T entity);
    void updateById(I id, T entity);
    void deleteById(I id);
}
