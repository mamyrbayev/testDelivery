package com.delivery.testDelivery.controllers.rest;

import com.delivery.testDelivery.controllers.BaseController;
import com.delivery.testDelivery.exceptions.ServiceException;
import com.delivery.testDelivery.models.dtos.OrderDto;
import com.delivery.testDelivery.models.entities.Order;
import com.delivery.testDelivery.models.entities.OrdersMeals;
import com.delivery.testDelivery.models.mappers.OrderMapper;
import com.delivery.testDelivery.repositories.OrdersMealsRepository;
import com.delivery.testDelivery.responses.OrderMealsResponse;
import com.delivery.testDelivery.responses.OrderResponse;
import com.delivery.testDelivery.services.OrderService;
import com.delivery.testDelivery.shared.utils.responses.SuccessResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController extends BaseController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final ObjectMapper objectMapper;
    private final OrdersMealsRepository ordersMealsRepository;

    public OrderController(OrderService orderService, OrderMapper orderMapper,
                           ObjectMapper objectMapper, OrdersMealsRepository ordersMealsRepository) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.objectMapper = objectMapper;
        this.ordersMealsRepository = ordersMealsRepository;
    }

    @GetMapping
    @ApiOperation("Получение всех заказов в грязном виде")
    public ResponseEntity<?> getAll() {
        List<Order> orders = orderService.findAll();
        List<Long> orderIds = new ArrayList<>();
        for(Order order:orders){
            orderIds.add(order.getId());
        }
        List<OrdersMeals> ordersMeals = ordersMealsRepository.findAllByDeletedAtIsNullAndOrder_IdIn(orderIds);
        List<OrderMealsResponse> orderMealsResponses = ordersMeals.stream().map((e) ->
                OrderMealsResponse.builder()
                        .id(e.getId())
                        .quantity(e.getQuantity())
                        .orderId(e.getOrder().getId())
                        .build())
                .collect(Collectors.toList());

        List<OrderResponse> orderResponses = orders.stream().map((e) ->
                OrderResponse.builder()
                        .id(e.getId())
                        .price(e.getOverallPrice())
                        .quantity(e.getOverallQuantity()).build())
                .collect(Collectors.toList());
        orderResponses.parallelStream().forEach(e -> {
            e.setOrderMealsResponses(orderMealsResponses.stream()
            .filter(qoE -> qoE.getOrderId().equals(e.getId())).collect(Collectors.toList()));
        });
        return buildResponse(orderResponses, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение заказа по ID")
    public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
        return buildResponse(orderMapper.toDto(orderService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Добавление заказа")
    public ResponseEntity<?> add(@RequestBody String object) throws ServiceException, IOException {
        Map<String, Object> map = objectMapper.readValue(object, Map.class);
        Object order = map.get("purchase");
        Object ordersMeals = map.get("purchaseMeals");
        Order order1 = objectMapper.convertValue(order, Order.class);
        List<OrdersMeals> ordersMeals1 = objectMapper.convertValue(ordersMeals,
                new TypeReference<List<OrdersMeals>>(){});
        orderService.save(order1, ordersMeals1);

        return buildResponse(object, HttpStatus.CREATED);
    }

    @DeleteMapping
    @ApiOperation("Удаление заказа")
    public ResponseEntity<?> delete(@RequestBody OrderDto orderDto) throws ServiceException{
        orderService.delete(orderMapper.toEntity(orderDto));
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление заказа по ID")
    public ResponseEntity<?> deleteById(@PathVariable Long id) throws ServiceException{
        orderService.deleteById(id);
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @ApiOperation("Обновление еды")
    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody OrderDto orderDto) throws ServiceException {
        Order order = orderService.update(orderMapper.toEntity(orderDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(orderMapper.toDto(order))
                .build(), HttpStatus.OK);
    }
}
