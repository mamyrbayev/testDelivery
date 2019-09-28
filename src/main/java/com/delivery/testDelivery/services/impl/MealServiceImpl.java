package com.delivery.testDelivery.services.impl;

import com.delivery.testDelivery.exceptions.ServiceException;
import com.delivery.testDelivery.models.entities.Meal;
import com.delivery.testDelivery.repositories.MealRepository;
import com.delivery.testDelivery.services.MealService;
import com.delivery.testDelivery.shared.utils.codes.ErrorCode;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;

    public MealServiceImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Override
    public List<Meal> findAll() {
        return mealRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public Meal findById(Long id) throws ServiceException {
        Optional<Meal> courseOptional = mealRepository.findById(id);
        return courseOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("lesson not found")
                .build());
    }

    @Override
    public Meal update(Meal meal) throws ServiceException {
        if(meal.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("lesson is null")
                    .build();
        }
        return mealRepository.save(meal);
    }

    @Override
    public Meal save(Meal meal) throws ServiceException {
        if(meal.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("lesson already exists")
                    .build();
        }
        return  mealRepository.save(meal);
    }

    @Override
    public void delete(Meal meal) throws ServiceException {
        if(meal.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("lesson is null")
                    .build();
        }
        meal = findById(meal.getId());
        meal.setDeletedAt(new Date());
        mealRepository.save(meal);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        Meal meal = findById(id);
        meal.setDeletedAt(new Date());
        mealRepository.save(meal);
    }

    @Override
    public List<Meal> findByCourseId(Long id) throws ServiceException {
        if (id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        return mealRepository.findAllByCategoryId(id);
    }
}
