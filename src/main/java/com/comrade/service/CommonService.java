package com.comrade.service;


import java.util.List;

public interface CommonService<E> {

    E save(E e);
    List<E> fetchAll();
    void update();
}
