package com.delivery.testDelivery.controllers.rest;

import com.delivery.testDelivery.controllers.BaseController;
import com.delivery.testDelivery.exceptions.ServiceException;
import com.delivery.testDelivery.models.dtos.OrderDto;
import com.delivery.testDelivery.models.entities.Order;
import com.delivery.testDelivery.models.mappers.OrderMapper;
import com.delivery.testDelivery.services.OrderService;
import com.delivery.testDelivery.shared.utils.responses.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController extends BaseController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping
    @ApiOperation("Получение всех заказов в грязном виде")
    public ResponseEntity<?> getAll() {
        return buildResponse(orderMapper.toDtoList(orderService.findAll()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение заказа по ID")
    public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
        return buildResponse(orderMapper.toDto(orderService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Добавление заказа")
    public ResponseEntity<?> add(@RequestBody OrderDto orderDto) throws ServiceException{
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
}
