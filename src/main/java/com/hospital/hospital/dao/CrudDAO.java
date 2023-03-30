package com.hospital.hospital.dao;

import java.util.List;

public interface CrudDAO<T> {

    List<T> getAll();

    T find(int id);

    T save(T entity);

    T update(T entity);

    int delete(int id);

}
