package com.delivery.testDelivery.services;

import com.delivery.testDelivery.exceptions.ServiceException;
import com.delivery.testDelivery.models.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Long id) throws ServiceException;
    Category update(Category category) throws ServiceException;
    Category save(Category category) throws ServiceException;
    void delete(Category category) throws ServiceException;
    void deleteById(Long id) throws ServiceException;
}
