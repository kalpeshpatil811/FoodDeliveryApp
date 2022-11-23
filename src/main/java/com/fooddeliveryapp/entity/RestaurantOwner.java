package com.fooddeliveryapp.entity;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantOwner {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;

    @NotNull(message = "Restaurant Name is mandatory")
    private String restaurantName;

    @NotNull(message = "Mobile Number is mandatory")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number should contain only 10 digits ")
    private String restaurantMobile;

    @NotNull(message = "First Name is mandatory")
    private String ownerFirstName;

    @NotNull(message = "Last Name is mandatory")
    private String ownerLastName;

    @NotNull(message = "City is mandatory")
    private String restaurantCity;

    @NotNull(message = "State is mandatory")
    private String restaurantState;


    @NotNull(message = "Pincode is mandatory")
    @Pattern(regexp = "^[1-9]{1}[0-9]{5}$", message = "Pincode should contain only 6 digits ")
    private String restaurantPincode;

    @NotNull(message = "Credentials are mandatory")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email")
    private Login login;

    @JsonManagedReference
    @OneToMany(mappedBy = "restaurantOwner",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Dish> dish;
}
