package com.bankapp.mapper;


import com.bankapp.entities.dao.AccountDAO;
import com.bankapp.entities.dao.UserDAO;
import com.bankapp.entities.dto.AccountDTO;
import com.bankapp.entities.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    @Mapping(source = "client.id", target = "clientId")
    AccountDTO accountDaoToDto(AccountDAO accountDAO);
    @Mapping(source = "clientId", target = "client.id")
    AccountDAO accountDtoToDao(AccountDTO accountDTO);
    UserDAO userDtoToDao(UserDTO accountDTO);
}