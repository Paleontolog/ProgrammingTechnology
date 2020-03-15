package com.bankapp.sequrity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JwtResponse {
    private String jwt;
    private Long id;
    private String login;
    private String phone;
    private List<String> roles;
}
