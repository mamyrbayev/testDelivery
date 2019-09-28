package com.delivery.testDelivery.models.dtos;

import com.delivery.testDelivery.models.dtos.base.BaseDto;
import com.delivery.testDelivery.models.entities.Category;
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
    private String name;

    @ApiModelProperty(notes = "Цена", readOnly = true)
    private Integer price;

    @ApiModelProperty(notes = "Ингридиенты", readOnly = true)
    private String ingredients;

    @ApiModelProperty(notes = "Описание еды", readOnly = true)
    private String description;

    @ApiModelProperty(notes = "Метка", readOnly = true)
    private String tag;

    @ApiModelProperty(notes = "Ссылка для фото", readOnly = true)
    private String photoPath;

    @ApiModelProperty(notes = "Категория", readOnly = true)
    private CategoryDto category;
}
