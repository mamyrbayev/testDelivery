package com.delivery.testDelivery.controllers.rest;

import com.delivery.testDelivery.controllers.BaseController;
import com.delivery.testDelivery.exceptions.ServiceException;
import com.delivery.testDelivery.models.dtos.UserDto;
import com.delivery.testDelivery.models.entities.Role;
import com.delivery.testDelivery.models.entities.User;
import com.delivery.testDelivery.models.mappers.UserMapper;
import com.delivery.testDelivery.services.UserService;
import com.delivery.testDelivery.shared.utils.codes.ErrorCode;
import com.delivery.testDelivery.shared.utils.responses.SuccessResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Api(description = "Точка входа для распознования")
public class UserController extends BaseController {

    private UserService userService;
    private UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    @ApiOperation("Получение всех пользователей в грязном виде")
    public ResponseEntity<?> getAll() {
        return buildResponse(userMapper.toDtoList(userService.findAll()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение по ID")
    public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
        return buildResponse(userMapper.toDto(userService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Регистрация пользователей")
    public ResponseEntity<?> add(@RequestBody UserDto userDto) throws ServiceException {
        User user = userMapper.toEntity(userDto);
        Role role = new Role();
        role.setId(Role.ROLE_USER);
        user.setRole(role);
        user = userService.save(user);
        return buildResponse(userMapper.toDto(user), HttpStatus.OK);
    }

    @PostMapping("/validate")
    @ApiOperation("Валидация логина")
    public ResponseEntity<?> validate(@RequestParam String login) throws ServiceException {
        User user = userService.findByLogin(login);
        if (user != null) {
            throw ServiceException.builder().message("Login exists").errorCode(ErrorCode.ALREADY_EXISTS).build();
        } else {
            return buildResponse(SuccessResponse.builder().message("OK").build(), HttpStatus.OK);
        }

    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ServiceException {
        userService.deleteById(id);
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody UserDto userDto) throws ServiceException {
        userService.delete(userMapper.toEntity(userDto));
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody UserDto userDto) throws ServiceException {
        User user = userService.update(userMapper.toEntity(userDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(userMapper.toDto(user))
                .build(), HttpStatus.OK);
    }

    @PostMapping("/current")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) throws ServiceException {
        String login = authentication.getName();
        User user = userService.findByLogin(login);
        return buildResponse(SuccessResponse.builder()
                .message("found")
                .data(userMapper.toDto(user))
                .build(), HttpStatus.OK);
    }

}
