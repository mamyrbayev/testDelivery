package com.delivery.testDelivery.models.dtos.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto {

    @ApiModelProperty(notes = "Уникальный идентификатор", readOnly = true)
    private Long id;

    @ApiModelProperty(notes = "Момент создания элемента", readOnly = true)
    private Date createdAt;

    @ApiModelProperty(notes = "Момент обновления элемента", readOnly = true)
    private Date updatedAt;

    @ApiModelProperty(notes = "Момент удаления элемента", readOnly = true)
    private Date deletedAt;

}
