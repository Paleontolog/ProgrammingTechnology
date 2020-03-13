package com.bankapp.controllers;

import com.bankapp.constants.OperationResults;
import com.bankapp.database.UserRepository;
import com.bankapp.entities.dao.AccountDAO;
import com.bankapp.entities.dto.AccountDTO;
import com.bankapp.entities.dto.OneAccountOperationDTO;
import com.bankapp.entities.dto.TransferOperationDTO;
import com.bankapp.mapper.EntityMapper;
import com.bankapp.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private BankService bankService;
    @Autowired
    private EntityMapper mapper;

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountDTO account) {
        bankService.createAccount(mapper.accountDtoToDao(account));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<List<AccountDTO>> getUserAccounts(@PathVariable("id") Long id) {
        List<AccountDTO> dtoList =
                bankService.getUserAccounts(id).stream()
                        .map(mapper::accountDaoToDto)
                        .collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<?> addMoney(@RequestBody OneAccountOperationDTO operation) {
        Optional<AccountDAO> fromAccount =
                bankService.getAccountById(operation.getAccountId());
        if (fromAccount.isPresent()) {
            bankService.addMoney(fromAccount.get(),
                    operation.getValue());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/withdraw")
    public ResponseEntity<?> withdrawMoney(@RequestBody OneAccountOperationDTO operation) {
        Optional<AccountDAO> fromAccount =
                bankService.getAccountById(operation.getAccountId());
        if (fromAccount.isPresent()) {
            bankService.withdrawMoney(fromAccount.get(),
                    operation.getValue());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/transfer")
    public ResponseEntity<?> transferMoney(@RequestBody TransferOperationDTO operation) {
        Optional<AccountDAO> fromAccount =
                bankService.getAccountById(operation.getFromAccountId());

        if (!fromAccount.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        OperationResults results =
                bankService.transfer(fromAccount.get(),
                        operation.getToNumber(), operation.getTransAmount());

        if (results == OperationResults.SUCCESS) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}