package com.fooddeliveryapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;
    @NotNull(message = "Admin name cannot be null")
    private String adminName;
    @NotNull(message = "user name cannot be null")
    private String adminUserName;
    @NotNull(message = "email cannot be null")
    private String adminEmail;
    @NotNull(message = "password cannot be null")
    private String password;

}
