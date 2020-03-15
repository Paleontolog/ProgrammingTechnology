package com.bankapp.sequrity.entities;

import com.bankapp.entities.dao.UserDAO;
import com.bankapp.entities.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ROLES", schema = "bank")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",  unique = true, nullable = false)
    private Long id;

    @Column(name = "NAME")
    @Enumerated(EnumType.STRING)
    private RolesEnum name;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserDAO> users;
}
