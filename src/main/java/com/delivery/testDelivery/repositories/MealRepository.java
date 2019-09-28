package com.delivery.testDelivery.repositories;

import com.delivery.testDelivery.models.entities.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findAllByDeletedAtIsNull();
    List<Meal> findAllByCategoryId(Long id);
}
