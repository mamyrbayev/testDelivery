package com.delivery.testDelivery.services.impl;

import com.delivery.testDelivery.exceptions.ServiceException;
import com.delivery.testDelivery.models.entities.Meal;
import com.delivery.testDelivery.models.entities.Order;
import com.delivery.testDelivery.repositories.OrderRepository;
import com.delivery.testDelivery.services.MealService;
import com.delivery.testDelivery.services.OrderService;
import com.delivery.testDelivery.shared.utils.codes.ErrorCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final MealService mealService;

    public OrderServiceImpl(OrderRepository orderRepository, MealService mealService) {
        this.orderRepository = orderRepository;
        this.mealService = mealService;
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
    public Order save(Order order) throws ServiceException {
        if(order.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("lesson already exists")
                    .build();
        }
        int quantity = 0;
        List<Meal> meals = order.getMeals();
        ArrayList<Meal> meals1 = new ArrayList<Meal>();
        for (int i = 0; i < meals.size(); i++){
            quantity += meals.get(i).getQuantity();
            Long n = meals.get(i).getId();
            Meal meal = mealService.findById(n);
            meal.setQuantity(meals.get(i).getQuantity());
            meals1.add(i, meal);
        }
        order.setStatus(0);
        order.setOverallQuantity(quantity);
        order.setMeals(meals1);
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

    @Override
    public Order setStatus(Long id, Integer status) throws ServiceException {
        Order order = orderRepository.getOne(id);
        if(order == null){
            ServiceException.builder()
                    .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                    .message("order not found")
                    .build();
        }
        order.setStatus(status);
        return order;
    }
}
