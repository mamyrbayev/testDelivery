package com.delivery.testDelivery.models.mappers;

import com.delivery.testDelivery.models.dtos.MealDto;
import com.delivery.testDelivery.models.dtos.OrderDto;
import com.delivery.testDelivery.models.entities.Meal;
import com.delivery.testDelivery.models.entities.Order;
import com.delivery.testDelivery.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper extends AbstractModelMapper<Order, OrderDto> {

    private final ModelMapper modelMapper;
    private final MealMapper mealMapper;

    public OrderMapper(ModelMapper modelMapper, MealMapper mealMapper) {
        this.modelMapper = modelMapper;
        this.mealMapper = mealMapper;
    }

    @Override
    public OrderDto toDto(Order order) {
        OrderDto orderDto = modelMapper.map(order, OrderDto.class);
        if (order.getMeals() != null) {
            orderDto.setMeals(mealMapper.toDtoList(order.getMeals()));
        }
        return orderDto;
    }

    @Override
    public Order toEntity(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        if (orderDto.getMeals() != null) {
            order.setMeals(mealMapper.toEntityList(orderDto.getMeals()));
        }
        return order;
    }

    @Override
    public List<OrderDto> toDtoList(List<Order> orders) {
        return orders.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> toEntityList(List<OrderDto> orderDtos) {
        return orderDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
