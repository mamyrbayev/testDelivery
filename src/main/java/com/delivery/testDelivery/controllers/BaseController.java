package com.delivery.testDelivery.controllers;

import com.delivery.testDelivery.exceptions.ServiceException;
import com.delivery.testDelivery.shared.utils.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class BaseController {

    protected ResponseEntity<?> buildResponse(Object data, HttpStatus httpStatus){
        return new ResponseEntity<>(data , httpStatus);
    }

    protected ResponseEntity<?> buildResponse(HttpStatus httpStatus){
        return new ResponseEntity<>(httpStatus);
    }

    protected ErrorResponse buildErrorResponse(ServiceException serviceException){
        return ErrorResponse.builder()
                .code(serviceException.getErrorCode())
                .message(serviceException.getMessage())
                .build();
    }

}
