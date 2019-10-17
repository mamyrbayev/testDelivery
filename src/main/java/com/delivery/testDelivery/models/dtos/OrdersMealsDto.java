package com.delivery.testDelivery.models.dtos;

import com.delivery.testDelivery.models.dtos.base.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@Getter
@NoArgsConstructor
@ApiModel(description = "Таблица которая описывает модель еды в заказах")
public class OrdersMealsDto extends BaseDto {

    @ApiModelProperty(notes = "Блюда", readOnly = true)
    private MealDto meal;

    @ApiModelProperty(notes = "Заказы", readOnly = true)
    private OrderDto orderDto;

    @ApiModelProperty(notes = "Количество", readOnly = true)
    private Integer quantity;
}
