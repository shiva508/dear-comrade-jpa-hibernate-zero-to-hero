package com.comrade.service;

import java.util.List;

public interface CommonService<T> {
    T save(T t);
    List<T> findAll();
    List<T> externalServiceCallBeforeDbCall();
    List<T> externalServiceCallAfterDbCall();
    List<T> creatingNewTransaction();
}
