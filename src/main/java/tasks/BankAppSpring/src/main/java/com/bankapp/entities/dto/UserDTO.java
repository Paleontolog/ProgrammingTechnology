package com.bankapp.entities.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Long id;
    private String login;
    private String password;
    private String address;
    private String phone;
}
