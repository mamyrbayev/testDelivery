package com.delivery.testDelivery.controllers.rest;

import com.delivery.testDelivery.controllers.BaseController;
import com.delivery.testDelivery.exceptions.ServiceException;
import com.delivery.testDelivery.models.dtos.MealDto;
import com.delivery.testDelivery.models.entities.Meal;
import com.delivery.testDelivery.models.mappers.MealMapper;
import com.delivery.testDelivery.services.MealService;
import com.delivery.testDelivery.shared.utils.responses.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meals")
public class MealController extends BaseController {
    private final MealService mealService;
    private final MealMapper mealMapper;

    public MealController(MealService mealService, MealMapper mealMapper) {
        this.mealService = mealService;
        this.mealMapper = mealMapper;
    }

    @GetMapping
    @ApiOperation("Получение всей еды в грязном виде")
    public ResponseEntity<?> getAll() {
        return buildResponse(mealMapper.toDtoList(mealService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    @ApiOperation("Получение еды по категориям")
    public ResponseEntity<?> getByCategoryId(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
        return buildResponse(mealMapper.toDtoList(mealService.findByCategoryId(id)), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение еды по ID")
    public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
        return buildResponse(mealMapper.toDto(mealService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Добавление еды")
    public ResponseEntity<?> add(@RequestBody MealDto mealDto) throws ServiceException{
        Meal meal = mealMapper.toEntity(mealDto);
        return buildResponse(mealMapper.toDto(mealService.save(meal)), HttpStatus.CREATED);
    }

    @DeleteMapping
    @ApiOperation("Удаление еды")
    public ResponseEntity<?> delete(@RequestBody MealDto mealDto) throws ServiceException{
        mealService.delete(mealMapper.toEntity(mealDto));
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление еды по ID")
    public ResponseEntity<?> deleteById(@PathVariable Long id) throws ServiceException{
        mealService.deleteById(id);
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @ApiOperation("Обновление еды")
    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody MealDto mealDto) throws ServiceException {
        Meal meal = mealService.update(mealMapper.toEntity(mealDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(mealMapper.toDto(meal))
                .build(), HttpStatus.OK);
    }

}
