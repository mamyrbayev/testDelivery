package com.delivery.testDelivery.repositories;

import com.delivery.testDelivery.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByDeletedAtIsNull();
    List<Order> findAllByDeletedAtIsNullAndUserId(Long id);
    List<Order> findAllByCreatedAtBetweenAndDeletedAtIsNull(Date from, Date till);

}
