package com.delivery.testDelivery.repositories;

import com.delivery.testDelivery.models.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByDeletedAtIsNull();
}
