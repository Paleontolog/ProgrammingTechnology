package com.bankapp.database;

import com.bankapp.entities.dao.UserDAO;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDAO, Long> {
    UserDAO findByLoginOrPhone(String login, String phone);
}
