package com.bankapp.database;

import com.bankapp.entities.dao.UserDAO;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserDAO, Long> {
    UserDAO findByLoginOrPhone(String login, String phone);
    Optional<UserDAO> findByLogin(String login);
    Optional<UserDAO> findByPhone(String phone);
    Boolean existsByLogin(String login);
    Boolean existsByPhone(String phone);
}
