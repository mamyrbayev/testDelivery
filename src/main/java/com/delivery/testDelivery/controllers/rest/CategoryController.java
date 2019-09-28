package com.delivery.testDelivery.controllers.rest;

import com.delivery.testDelivery.controllers.BaseController;
import com.delivery.testDelivery.exceptions.ServiceException;
import com.delivery.testDelivery.models.dtos.CategoryDto;
import com.delivery.testDelivery.models.entities.Category;
import com.delivery.testDelivery.models.mappers.CategoryMapper;
import com.delivery.testDelivery.services.CategoryService;
import com.delivery.testDelivery.shared.utils.responses.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController extends BaseController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping
    @ApiOperation("Получение всех категории в грязном виде")
    public ResponseEntity<?> getAll() {
        return buildResponse(categoryMapper.toDtoList(categoryService.findAll()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение категории по ID")
    public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
        return buildResponse(categoryMapper.toDto(categoryService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Добавление категории")
    public ResponseEntity<?> add(@RequestBody CategoryDto categoryDto) throws ServiceException{
        Category category = categoryMapper.toEntity(categoryDto);
        return buildResponse(categoryMapper.toDto(categoryService.save(category)), HttpStatus.CREATED);
    }

    @DeleteMapping
    @ApiOperation("Удаление категории")
    public ResponseEntity<?> delete(@RequestBody CategoryDto categoryDto) throws ServiceException{
        categoryService.delete(categoryMapper.toEntity(categoryDto));
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление категории по ID")
    public ResponseEntity<?> deleteById(@PathVariable Long id) throws ServiceException{
        categoryService.deleteById(id);
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @ApiOperation("Обновление категории")
    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody CategoryDto categoryDto) throws ServiceException {
        Category category = categoryService.update(categoryMapper.toEntity(categoryDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(categoryMapper.toDto(category))
                .build(), HttpStatus.OK);
    }


}
