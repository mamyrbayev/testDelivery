package com.delivery.testDelivery.controllers.rest;

import com.delivery.testDelivery.controllers.BaseController;
import com.delivery.testDelivery.exceptions.ServiceException;
import com.delivery.testDelivery.models.dtos.OrderDto;
import com.delivery.testDelivery.models.entities.Order;
import com.delivery.testDelivery.models.mappers.OrderMapper;
import com.delivery.testDelivery.models.requests.OrderStatRequest;
import com.delivery.testDelivery.services.OrderService;
import com.delivery.testDelivery.shared.utils.responses.SuccessResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController extends BaseController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final ObjectMapper objectMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper,
                           ObjectMapper objectMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    @ApiOperation("Получение всех заказов в грязном виде")
    public ResponseEntity<?> getAll() {
        return buildResponse(orderMapper.toDtoList(orderService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    @ApiOperation("Получение всех заказов по пользователям")
    public ResponseEntity<?> getAll(@PathVariable Long id) {
        return buildResponse(orderMapper.toDtoList(orderService.findAllByUserId(id)), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение заказа по ID")
    public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
        return buildResponse(orderMapper.toDto(orderService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Добавление заказа")
    public ResponseEntity<?> add(@RequestBody OrderDto orderDto) throws ServiceException, IOException {
        Order order = orderMapper.toEntity(orderDto);
        return buildResponse(orderMapper.toDto(orderService.save(order)), HttpStatus.CREATED);
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

    @ApiOperation("Обновление статуса")
    @PutMapping("/status")
    public ResponseEntity<?> setStatus(@RequestParam Long id, @RequestParam Integer status) throws ServiceException {
        Order order = orderService.setStatus(id, status);
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(orderMapper.toDto(order))
                .build(), HttpStatus.OK);
    }

    @PostMapping("/stat")
    @ApiOperation("Получение статистики по дате")
    public ResponseEntity<?> getOne(@ApiParam("ID элемента") @RequestBody OrderStatRequest orderStatRequest) throws ServiceException {
        return buildResponse(SuccessResponse.builder()
                .message("Statistics")
                .data(orderService.getOrderAmount(orderStatRequest.getFrom(), orderStatRequest.getTill()))
                .build(), HttpStatus.OK);
    }
}
