package ru.bigcheese.jsalon.ee.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by BigCheese on 10.03.15.
 */
public interface BaseDao<T extends Serializable, K extends Serializable> {
    void persist(T model);
    void update(T model);
    void delete(K id);
    T findById(K id);
    List<T> findAll();
    Long countAll();
    boolean existsById(Long id);
}
