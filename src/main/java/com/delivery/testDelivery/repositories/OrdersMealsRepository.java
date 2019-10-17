package com.delivery.testDelivery.repositories;

import com.delivery.testDelivery.models.entities.OrdersMeals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersMealsRepository extends JpaRepository<OrdersMeals, Long> {
    List<OrdersMeals> findAllByDeletedAtIsNullAndOrder_IdIn(List<Long> ids);
}
