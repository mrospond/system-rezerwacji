package com.example.reservationsystem.database.dao;

import com.example.reservationsystem.database.conditions.AbstractQueryCondition;

import java.util.List;

public interface Dao<I, T> {
    T getById(I id);
    void insert(T entity);
    void updateById(I id, T entity);
    void deleteById(I id);
    List<T> getAll(List<AbstractQueryCondition> conditions);
}
