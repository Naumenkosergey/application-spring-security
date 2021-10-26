package by.naumenko.dao;

import by.naumenko.entity.BaseEntity;

import java.util.List;

public interface BaseDao<T extends BaseEntity> {
    T findOne(Long id);

    T save(T entity);

    List<T> findAll();

    void update(T entity);

    void remove(Long id);
}
