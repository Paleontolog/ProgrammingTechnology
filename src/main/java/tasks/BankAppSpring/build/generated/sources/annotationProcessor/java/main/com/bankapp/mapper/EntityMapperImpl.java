package com.bankapp.mapper;

import com.bankapp.entities.dao.AccountDAO;
import com.bankapp.entities.dao.OperationDAO;
import com.bankapp.entities.dao.UserDAO;
import com.bankapp.entities.dto.AccountDTO;
import com.bankapp.entities.dto.OperationDTO;
import com.bankapp.entities.dto.UserDTO;
import java.sql.Date;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-21T15:53:14+0400",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_241 (Oracle Corporation)"
)
@Component
public class EntityMapperImpl implements EntityMapper {

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
        accountDTO.setIsMain( accountDAO.getIsMain() );

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
        accountDAO.setAccCode( accountDTO.getAccCode() );
        accountDAO.setIsMain( accountDTO.getIsMain() );

        return accountDAO;
    }

    @Override
    public UserDAO userDtoToDao(UserDTO accountDTO) {
        if ( accountDTO == null ) {
            return null;
        }

        UserDAO userDAO = new UserDAO();

        userDAO.setLogin( accountDTO.getLogin() );
        userDAO.setPassword( accountDTO.getPassword() );
        userDAO.setAddress( accountDTO.getAddress() );
        userDAO.setPhone( accountDTO.getPhone() );

        return userDAO;
    }

    @Override
    public UserDTO userDaoToDto(UserDAO accountDTO) {
        if ( accountDTO == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setLogin( accountDTO.getLogin() );
        userDTO.setPassword( accountDTO.getPassword() );
        userDTO.setAddress( accountDTO.getAddress() );
        userDTO.setPhone( accountDTO.getPhone() );

        return userDTO;
    }

    @Override
    public OperationDTO operationDaoToDto(OperationDAO operationDAO) {
        if ( operationDAO == null ) {
            return null;
        }

        OperationDTO operationDTO = new OperationDTO();

        operationDTO.setToUser( operationDAOToUserId( operationDAO ) );
        operationDTO.setFromUser( operationDAOFromUserId( operationDAO ) );
        operationDTO.setId( operationDAO.getId() );
        if ( operationDAO.getOperationDate() != null ) {
            operationDTO.setOperationDate( new Date( operationDAO.getOperationDate().getTime() ) );
        }
        operationDTO.setTransAmount( operationDAO.getTransAmount() );
        operationDTO.setBeforeTransfer( operationDAO.getBeforeTransfer() );
        operationDTO.setAfterTransfer( operationDAO.getAfterTransfer() );

        return operationDTO;
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

    private Long operationDAOToUserId(OperationDAO operationDAO) {
        if ( operationDAO == null ) {
            return null;
        }
        AccountDAO toUser = operationDAO.getToUser();
        if ( toUser == null ) {
            return null;
        }
        Long id = toUser.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long operationDAOFromUserId(OperationDAO operationDAO) {
        if ( operationDAO == null ) {
            return null;
        }
        AccountDAO fromUser = operationDAO.getFromUser();
        if ( fromUser == null ) {
            return null;
        }
        Long id = fromUser.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
