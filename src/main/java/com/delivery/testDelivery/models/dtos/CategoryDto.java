package com.delivery.testDelivery.models.dtos;

import com.delivery.testDelivery.models.dtos.base.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "Таблица которая описывает модель категории еды")
public class CategoryDto extends BaseDto {
    @ApiModelProperty(notes = "Наименование", readOnly = true)
    private String name;

    @ApiModelProperty(notes = "Ссылка для фото", readOnly = true)
    private String photoPath;
}
