package com.delivery.testDelivery.models.mappers;

import com.delivery.testDelivery.models.dtos.OrdersMealsDto;
import com.delivery.testDelivery.models.entities.OrdersMeals;
import com.delivery.testDelivery.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrdersMealsMapper extends AbstractModelMapper<OrdersMeals, OrdersMealsDto> {

    private final ModelMapper modelMapper;

    public OrdersMealsMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public OrdersMealsDto toDto(OrdersMeals ordersMeals) {
        return modelMapper.map(ordersMeals, OrdersMealsDto.class);
    }

    @Override
    public OrdersMeals toEntity(OrdersMealsDto ordersMealsDto) {
        return modelMapper.map(ordersMealsDto, OrdersMeals.class);
    }

    @Override
    public List<OrdersMealsDto> toDtoList(List<OrdersMeals> ordersMeals) {
        return ordersMeals.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrdersMeals> toEntityList(List<OrdersMealsDto> ordersMealsDtos) {
        return ordersMealsDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
