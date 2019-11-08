package com.delivery.testDelivery.services;

import com.delivery.testDelivery.exceptions.ServiceException;
import com.delivery.testDelivery.models.entities.Meal;

import java.util.List;

public interface MealService {
    List<Meal> findAll();
    Meal findById(Long id) throws ServiceException;
    Meal update(Meal meal) throws ServiceException;
    Meal save(Meal meal) throws ServiceException;
    void delete(Meal meal) throws ServiceException;
    void deleteById(Long id) throws ServiceException;
    List<Meal> findByCourseId(Long id) throws ServiceException;
    List<Meal> findByCategoryId(Long id) throws ServiceException;

}
