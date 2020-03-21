package com.bankapp.sequrity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class JwtResponse {
    private String jwt;
    private String login;
    private String phone;
    private List<String> roles;

    public JwtResponse(String jwt, String login, String phone, List<String> roles) {
        this.jwt = "Bearer " + jwt;
        this.login = login;
        this.phone = phone;
        this.roles = roles;
    }
}
