package com.bankapp.database;

import com.bankapp.entities.dao.AccountDAO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<AccountDAO, Long> {
    List<AccountDAO> findAllByClientId(Long userId);
    List<AccountDAO> findAllByClient_Login(String userId);
    List<AccountDAO> findAll();
}
