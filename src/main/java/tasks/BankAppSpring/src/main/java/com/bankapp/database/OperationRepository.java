package com.bankapp.database;

import com.bankapp.entities.dao.OperationDAO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OperationRepository extends CrudRepository<OperationDAO, Long> {
    List<OperationDAO> findAllByFromUserIdOrToUserId(Long fromId, Long toId);
    List<OperationDAO> findAll();
}
