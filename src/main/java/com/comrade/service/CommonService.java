package com.comrade.service;

import java.util.List;

public interface CommonService<T> {
    public T save(T t);
    public List<T> findAll();
}
