package com.delivery.testDelivery.models.dtos;

import com.delivery.testDelivery.models.dtos.base.BaseDto;
//import com.delivery.testDelivery.models.entities.Rating;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
@ApiModel(description = "Таблица которая описывает модель еды")
public class MealDto extends BaseDto {
    @ApiModelProperty(notes = "Наименование", readOnly = true)
    private String title;

    @ApiModelProperty(notes = "Цена", readOnly = true)
    private Double price;

    @ApiModelProperty(notes = "Ингридиенты", readOnly = true)
    private String ingredients;

    @ApiModelProperty(notes = "Описание еды", readOnly = true)
    private String description;

    @ApiModelProperty(notes = "Метка", readOnly = true)
    private String tag;

//    @ApiModelProperty(notes = "Оценка", readOnly = true)
//    private Rating rating;

    @ApiModelProperty(notes = "Ссылка для фото", readOnly = true)
    private String url;

    @ApiModelProperty(notes = "Количество", readOnly = true)
    private Integer quantity;

    @ApiModelProperty(notes = "Категория", readOnly = true)
    private CategoryDto category;

    @ApiModelProperty(notes = "в корзине?")
    private Boolean isAddedToCart = false;

    @ApiModelProperty(notes = "в корзине?")
    private Boolean isAddedBtn = false;

}
