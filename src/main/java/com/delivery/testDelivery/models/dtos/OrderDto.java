package com.delivery.testDelivery.models.dtos;

import com.delivery.testDelivery.models.dtos.base.BaseDto;
import com.delivery.testDelivery.models.entities.Meal;
import com.delivery.testDelivery.models.entities.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
@ApiModel(description = "Таблица которая описывает модель заказов")
public class OrderDto extends BaseDto {
    @ApiModelProperty(notes = "Общее количество", readOnly = true)
    private Integer overallQuantity;

    @ApiModelProperty(notes = "Общая цена", readOnly = true)
    private Integer overallPrice;

    @ApiModelProperty(notes = "Пользователь", readOnly = true)
    private User user;

    @ApiModelProperty(notes = "Блюда", readOnly = true)
    private List<OrdersMealsDto> ordersMeals;

    @ApiModelProperty(notes = "Тип оплаты", readOnly = true)
    private String paymentType;
}
