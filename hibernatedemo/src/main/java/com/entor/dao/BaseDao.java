package com.entor.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BaseDao<T> {

    T getById(Serializable id);

    T insert(T o);

    void insert(List<T> list);

    void delete(List<T> list);

    void update(T t);

    void update(List<T> list);

    List<T> findByProperty(String name, Object value);

    List<T> findByProperty(Map<String, Object> conditionMap);

    List<T> findByPage(Map<String, Object> params);

    Integer count(Map<String, Object> params);

    <T> T get(CharSequence queryString, Map<String, Object> params);
}
