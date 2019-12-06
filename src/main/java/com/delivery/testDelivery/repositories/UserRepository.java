package com.delivery.testDelivery.repositories;

import com.delivery.testDelivery.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByDeletedAtIsNull();
    List<User> findAllByDeletedAtIsNullAndRoleId(Long id);
}
