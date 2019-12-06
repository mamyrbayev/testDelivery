package com.delivery.testDelivery.services.impl;

import com.delivery.testDelivery.exceptions.ServiceException;
import com.delivery.testDelivery.models.entities.Category;
import com.delivery.testDelivery.models.entities.Meal;
import com.delivery.testDelivery.repositories.CategoryRepository;
import com.delivery.testDelivery.services.CategoryService;
import com.delivery.testDelivery.services.MealService;
import com.delivery.testDelivery.shared.utils.codes.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final MealService mealService;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public Category findById(Long id) throws ServiceException {
        Optional<Category> courseOptional = categoryRepository.findById(id);
        return courseOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("course not found")
                .build());    }

    @Override
    public Category update(Category category) throws ServiceException {
        if(category.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("course is null")
                    .build();
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category save(Category category) throws ServiceException {
        if(category.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("course already exists")
                    .build();
        }
        return  categoryRepository.save(category);
    }

    @Override
    public void delete(Category category) throws ServiceException {
        if(category.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("category is null")
                    .build();
        }
        List<Meal> meals = mealService.findByCategoryId(category.getId());
        if(meals.size() == 0) {
            Category category1 = findById(category.getId());
            category.setDeletedAt(new Date());
            categoryRepository.save(category1);
        }else {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("meal is null")
                    .build();
        }
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        List<Meal> meals = mealService.findByCategoryId(id);
        if(meals.size() == 0) {
            Category category = findById(id);
            category.setDeletedAt(new Date());
            categoryRepository.save(category);
        }else {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("meal is null")
                    .build();
        }

    }
}
