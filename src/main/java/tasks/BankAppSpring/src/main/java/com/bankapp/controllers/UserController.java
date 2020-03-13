package com.bankapp.controllers;

import com.bankapp.constants.OperationResults;
import com.bankapp.entities.dto.UserDTO;
import com.bankapp.mapper.EntityMapper;
import com.bankapp.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private BankService bankService;
    @Autowired
    private EntityMapper mapper;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO user) {
        if (bankService.registration(mapper.userDtoToDao(user)) == OperationResults.USER_ALREADY_EXISTS) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}