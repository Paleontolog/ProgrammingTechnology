package com.bankapp.entities.dao;

import com.bankapp.sequrity.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "USER", schema = "bank")
public class UserDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;
    @Column(name = "LOGIN", nullable = false)
    private String login;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "ADDRESS", nullable = false)
    private String address;
    @OneToMany(mappedBy = "client", fetch=FetchType.LAZY)
    private List<AccountDAO> accounts;

    @Column(name = "PHONE", nullable = false, unique = true)
    private String phone;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> userRoles;

    public void addAccount(AccountDAO accountDAO) {
        accounts.add(accountDAO);
    }

}
