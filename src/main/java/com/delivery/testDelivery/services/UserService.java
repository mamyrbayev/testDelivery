package com.delivery.testDelivery.services;

import com.delivery.testDelivery.exceptions.ServiceException;
import com.delivery.testDelivery.models.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

    User findById(Long id) throws ServiceException;
    List<User> findAll();
    List<User> findAllByRole(Long id);
    List<User> findAllWithDeleted();
    User update(User user) throws ServiceException ;
    User save(User user) throws ServiceException ;
    void delete(User user) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;
    Set getAuthority(User user);
    User findByLogin(String login);
}
