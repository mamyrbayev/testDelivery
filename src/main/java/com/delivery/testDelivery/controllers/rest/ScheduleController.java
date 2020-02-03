package com.delivery.testDelivery.controllers.rest;

import com.delivery.testDelivery.controllers.BaseController;
import com.delivery.testDelivery.exceptions.ServiceException;
import com.delivery.testDelivery.models.dtos.ScheduleDto;
import com.delivery.testDelivery.models.entities.Schedule;
import com.delivery.testDelivery.models.mappers.ScheduleMapper;
import com.delivery.testDelivery.services.ScheduleService;
import com.delivery.testDelivery.shared.utils.responses.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/schedule")
public class ScheduleController extends BaseController {
    private final ScheduleService scheduleService;
    private final ScheduleMapper scheduleMapper;

    @GetMapping
    @ApiOperation("Получение всей таблицы в грязном виде")
    public ResponseEntity<?> getAll() {
        return buildResponse(scheduleMapper.toDtoList(scheduleService.findAll()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение объекта по ID")
    public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
        return buildResponse(scheduleMapper.toDto(scheduleService.findById(id)), HttpStatus.OK);
    }

    @ApiOperation("Обновление объекта")
    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody ScheduleDto scheduleDto) throws ServiceException {
        Schedule schedule = scheduleService.update(scheduleMapper.toEntity(scheduleDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(scheduleMapper.toDto(schedule))
                .build(), HttpStatus.OK);
    }
}
