package com.delivery.testDelivery.services.impl;

import com.delivery.testDelivery.exceptions.ServiceException;
import com.delivery.testDelivery.models.entities.Meal;
import com.delivery.testDelivery.models.entities.Order;
import com.delivery.testDelivery.models.entities.OrdersMeals;
import com.delivery.testDelivery.repositories.OrderRepository;
import com.delivery.testDelivery.repositories.OrdersMealsRepository;
import com.delivery.testDelivery.services.OrderService;
import com.delivery.testDelivery.shared.utils.codes.ErrorCode;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrdersMealsRepository ordersMealsRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrdersMealsRepository ordersMealsRepository) {

        this.orderRepository = orderRepository;
        this.ordersMealsRepository = ordersMealsRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public Order findById(Long id) throws ServiceException {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("order not found")
                .build());
    }

    @Override
    public Order update(Order order) throws ServiceException {
        if(order.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("lesson is null")
                    .build();
        }
        return orderRepository.save(order);
    }

    @Override
    public Order save(Order order, List<OrdersMeals> ordersMeals) throws ServiceException {
        if(order.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("lesson already exists")
                    .build();
        }
        int quantity = 0;
        for (OrdersMeals meal:ordersMeals){
            quantity += meal.getQuantity();
            meal.setOrder(order);
            meal.setMeal(meal.getMeal());
        }
        ordersMealsRepository.saveAll(ordersMeals);
        order.setOverallQuantity(quantity);
        return  orderRepository.save(order);
    }

    @Override
    public void delete(Order order) throws ServiceException {
        if(order.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("x is null")
                    .build();
        }
        order = findById(order.getId());
        order.setDeletedAt(new Date());
        orderRepository.save(order);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("lesson is null")
                    .build();
        }
        Order order = findById(id);
        order.setDeletedAt(new Date());
        orderRepository.save(order);
    }
}
