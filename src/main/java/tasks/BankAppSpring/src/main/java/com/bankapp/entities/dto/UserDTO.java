package com.bankapp.entities.dto;

import com.bankapp.sequrity.entities.Role;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Long id;
    private String login;
    private String password;
    private String address;
    private String phone;
    private Set<Role> roles;
}
