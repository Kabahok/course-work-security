package com.egoravdeev.courseworkbackend.dao.impl;

import com.egoravdeev.courseworkbackend.model.Order;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface OrderDAOImpl {
//    добавление
//    получение

    void addOrder(Order order) throws ParseException;
    List<Order> findAll();
    Optional<Order> findById(int id);
}
