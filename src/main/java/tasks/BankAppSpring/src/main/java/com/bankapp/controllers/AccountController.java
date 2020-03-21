package com.bankapp.controllers;

import com.bankapp.constants.OperationResults;
import com.bankapp.entities.dao.AccountDAO;
import com.bankapp.entities.dto.*;
import com.bankapp.mapper.EntityMapper;
import com.bankapp.sequrity.response.MessageResponse;
import com.bankapp.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private BankService bankService;
    @Autowired
    private EntityMapper mapper;

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> createAccount(@RequestBody AccountCreateRequest account) {
        OperationResults results = bankService.createAccount(account);
        if (results == OperationResults.USER_DOES_NOT_EXISTS) {
            return new ResponseEntity<>(new MessageResponse("User not found!"),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{name}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<AccountDTO>> getUserAccounts(@PathVariable("name") String name) {
        List<AccountDTO> dtoList =
                bankService.getUserAccounts(name).stream()
                        .map(mapper::accountDaoToDto)
                        .collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @PutMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> addMoney(@RequestBody OneAccountOperationDTO operation) {
        Optional<AccountDAO> fromAccount =
                bankService.getAccountById(operation.getAccountId());
        if (fromAccount.isPresent()) {
            bankService.addMoney(fromAccount.get(), operation.getValue());
            return new ResponseEntity<>(new MessageResponse("Operation successful!"),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Account not found!"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/withdraw")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> withdrawMoney(@RequestBody OneAccountOperationDTO operation) {
        Optional<AccountDAO> fromAccount =
                bankService.getAccountById(operation.getAccountId());
        if (fromAccount.isPresent()) {
            bankService.withdrawMoney(fromAccount.get(), operation.getValue());
            return new ResponseEntity<>(new MessageResponse("Operation successful!"),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Account not found!"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/transfer")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> transferMoney(@RequestBody TransferOperationDTO operation) {
        Optional<AccountDAO> fromAccount =
                bankService.getAccountById(operation.getFromAccountId());

        if (!fromAccount.isPresent()) {
            return new ResponseEntity<>(new MessageResponse("Account not found!"),
                    HttpStatus.BAD_REQUEST);
        }

        OperationResults results =
                bankService.transfer(fromAccount.get(),
                        operation.getToNumber(), operation.getTransAmount());

        if (results == OperationResults.SUCCESS) {
            return new ResponseEntity<>(new MessageResponse("Operation success"),
                    HttpStatus.OK);
        }

        return new ResponseEntity<>(new MessageResponse(results.toString()),
                HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/history/{name}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getHistory(@PathVariable("name") String name) {
        List<OperationDTO> dtoList = bankService.getUserOperationStory(name);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }


    @GetMapping("/admin/history")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getHistoryAll() {
        List<OperationDTO> dtoList = bankService.getAllHistory();
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/admin/accounts")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAccountsAll() {
        List<AccountDTO> dtoList = bankService.getAllAccounts();
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUserAll() {
        List<UserDTO> dtoList = bankService.getAllUser();
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }


}