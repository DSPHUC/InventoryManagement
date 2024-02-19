package com.example.inventorymanagement.service;

import java.util.List;

public interface IGeneralService<E,T> {
    List<E> findAll();

    E findById(T t);

    void save(E e);

    void delete(T t);
}
