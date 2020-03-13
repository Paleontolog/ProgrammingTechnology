package com.bankapp.mapper;

import com.bankapp.entities.dao.AccountDAO;
import com.bankapp.entities.dao.UserDAO;
import com.bankapp.entities.dto.AccountDTO;
import com.bankapp.entities.dto.UserDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-14T01:27:29+0400",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_241 (Oracle Corporation)"
)
@Component
public class EntityMapperImpl implements EntityMapper {

    @Override
    public UserDTO userDaoToDto(UserDAO userDAO) {
        if ( userDAO == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( userDAO.getId() );
        userDTO.setLogin( userDAO.getLogin() );
        userDTO.setPassword( userDAO.getPassword() );
        userDTO.setAddress( userDAO.getAddress() );
        userDTO.setPhone( userDAO.getPhone() );

        return userDTO;
    }

    @Override
    public AccountDTO accountDaoToDto(AccountDAO accountDAO) {
        if ( accountDAO == null ) {
            return null;
        }

        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setClientId( accountDAOClientId( accountDAO ) );
        accountDTO.setId( accountDAO.getId() );
        accountDTO.setAmount( accountDAO.getAmount() );
        accountDTO.setAccCode( accountDAO.getAccCode() );

        return accountDTO;
    }

    @Override
    public AccountDAO accountDtoToDao(AccountDTO accountDTO) {
        if ( accountDTO == null ) {
            return null;
        }

        AccountDAO accountDAO = new AccountDAO();

        accountDAO.setClient( accountDTOToUserDAO( accountDTO ) );
        accountDAO.setId( accountDTO.getId() );
        accountDAO.setAmount( accountDTO.getAmount() );
        accountDAO.setAccCode( accountDTO.getAccCode() );

        return accountDAO;
    }

    @Override
    public UserDAO userDtoToDao(UserDTO accountDTO) {
        if ( accountDTO == null ) {
            return null;
        }

        UserDAO userDAO = new UserDAO();

        userDAO.setId( accountDTO.getId() );
        userDAO.setLogin( accountDTO.getLogin() );
        userDAO.setPassword( accountDTO.getPassword() );
        userDAO.setAddress( accountDTO.getAddress() );
        userDAO.setPhone( accountDTO.getPhone() );

        return userDAO;
    }

    private Long accountDAOClientId(AccountDAO accountDAO) {
        if ( accountDAO == null ) {
            return null;
        }
        UserDAO client = accountDAO.getClient();
        if ( client == null ) {
            return null;
        }
        Long id = client.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected UserDAO accountDTOToUserDAO(AccountDTO accountDTO) {
        if ( accountDTO == null ) {
            return null;
        }

        UserDAO userDAO = new UserDAO();

        userDAO.setId( accountDTO.getClientId() );

        return userDAO;
    }
}
