package com.delivery.testDelivery.services.impl;

import com.delivery.testDelivery.exceptions.ServiceException;
import com.delivery.testDelivery.models.entities.Schedule;
import com.delivery.testDelivery.repositories.ScheduleRepository;
import com.delivery.testDelivery.services.ScheduleService;
import com.delivery.testDelivery.shared.utils.codes.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> findAll() {
        return scheduleRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public Schedule findById(Long id) throws ServiceException {
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(id);
        return scheduleOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("schedule not found")
                .build());
    }

    @Override
    public Schedule update(Schedule schedule) throws ServiceException {
        if(schedule.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("schedule is null")
                    .build();
        }
        return scheduleRepository.save(schedule);
    }
}
