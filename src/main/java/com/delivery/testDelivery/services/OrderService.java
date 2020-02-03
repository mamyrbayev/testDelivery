package com.delivery.testDelivery.services;

import com.delivery.testDelivery.exceptions.ServiceException;
import com.delivery.testDelivery.models.entities.Order;
import com.delivery.testDelivery.models.responses.OrderStatResponse;
import io.swagger.models.auth.In;

import java.util.Date;
import java.util.List;

public interface OrderService { 
    List<Order> findAll();
    List<Order> findAllByUserId(Long id);
    Order findById(Long id) throws ServiceException;
    Order update(Order order) throws ServiceException;
    Order save(Order order) throws ServiceException;
    void delete(Order order) throws ServiceException;
    void deleteById(Long id) throws ServiceException;
    Order setStatus(Long id, Integer status) throws ServiceException;
    OrderStatResponse getOrderAmount(Date from, Date till);
}
