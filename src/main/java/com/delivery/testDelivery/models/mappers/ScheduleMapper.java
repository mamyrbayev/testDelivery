package com.delivery.testDelivery.models.mappers;

import com.delivery.testDelivery.models.dtos.ScheduleDto;
import com.delivery.testDelivery.models.entities.Schedule;
import com.delivery.testDelivery.shared.utils.mappers.AbstractModelMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ScheduleMapper extends AbstractModelMapper<Schedule, ScheduleDto> {
    private final ModelMapper modelMapper;

    @Override
    public ScheduleDto toDto(Schedule schedule) {
        return modelMapper.map(schedule, ScheduleDto.class);
    }

    @Override
    public Schedule toEntity(ScheduleDto scheduleDto) {
        return modelMapper.map(scheduleDto, Schedule.class);
    }

    @Override
    public List<ScheduleDto> toDtoList(List<Schedule> schedules) {
        return schedules.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Schedule> toEntityList(List<ScheduleDto> scheduleDtos) {
        return scheduleDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
