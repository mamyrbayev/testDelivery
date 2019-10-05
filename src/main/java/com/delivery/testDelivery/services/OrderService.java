package com.delivery.testDelivery.services;

import com.delivery.testDelivery.exceptions.ServiceException;
import com.delivery.testDelivery.models.entities.Order;

import java.util.List;

public interface OrderService { 
    List<Order> findAll();
    Order findById(Long id) throws ServiceException;
    Order update(Order order) throws ServiceException;
    Order save(Order order) throws ServiceException;
    void delete(Order order) throws ServiceException;
    void deleteById(Long id) throws ServiceException;
}
