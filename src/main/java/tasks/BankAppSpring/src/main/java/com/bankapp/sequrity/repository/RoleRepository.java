package com.bankapp.sequrity.repository;

import com.bankapp.sequrity.entities.Role;
import com.bankapp.sequrity.entities.RolesEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(RolesEnum name);
}
