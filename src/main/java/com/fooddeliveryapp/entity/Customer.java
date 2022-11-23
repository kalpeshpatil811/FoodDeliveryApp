package com.fooddeliveryapp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @NotNull(message = "First name cannot be null")
    private String firstName;
    @NotNull(message = "Last name cannot be null")
    private String lastName;
    @NotNull(message = "Mobile number type cannot be null")
    private String mobileNumber;
    @NotNull(message = "Email cannot be null")
    private String email;
    @NotNull(message = "City cannot be null")
    private String city;
    @NotNull(message = "Address cannot be null")
    private String address;
    @NotNull(message = "Pincode cannot be null")
    private String pincode;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

}