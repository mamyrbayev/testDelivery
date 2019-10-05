package com.delivery.testDelivery.models.mappers;

import com.delivery.testDelivery.models.dtos.MealDto;
import com.delivery.testDelivery.models.entities.Meal;
import com.delivery.testDelivery.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MealMapper extends AbstractModelMapper<Meal, MealDto> {

    private ModelMapper modelMapper;
    private CategoryMapper categoryMapper;

    @Autowired
    public MealMapper(ModelMapper modelMapper, CategoryMapper categoryMapper) {
        this.modelMapper = modelMapper;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public MealDto toDto(Meal meal) {
        MealDto mealDto = modelMapper.map(meal, MealDto.class);
        if (meal.getCategory() != null) {
            mealDto.setCategory(categoryMapper.toDto(meal.getCategory()));
        }
        return mealDto;
    }

    @Override
    public Meal toEntity(MealDto mealDto) {
        Meal meal = modelMapper.map(mealDto, Meal.class);
        if (mealDto.getCategory() != null) {
            meal.setCategory(categoryMapper.toEntity(mealDto.getCategory()));
        }
        return meal;
    }

    @Override
    public List<MealDto> toDtoList(List<Meal> meals) {
        return meals.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> toEntityList(List<MealDto> mealDtos) {
        return mealDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
