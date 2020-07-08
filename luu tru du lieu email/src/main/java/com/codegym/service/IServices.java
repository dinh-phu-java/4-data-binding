package com.codegym.service;

import java.util.List;

public interface IServices<T> {
    public List<T> findAll();

    public T findById(int id);

    void save(T model);
    void remove(int id);
    void update(int id,T model);
}
