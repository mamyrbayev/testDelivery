package com.delivery.testDelivery.models.dtos;

import com.delivery.testDelivery.models.dtos.base.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto extends BaseDto {
    @ApiModelProperty(notes = "День", readOnly = true)
    private String day;

    @ApiModelProperty(notes = "Время открытия", readOnly = true)
    private LocalTime opening;

    @ApiModelProperty(notes = "Время закрытия", readOnly = true)
    private LocalTime closing;
}
