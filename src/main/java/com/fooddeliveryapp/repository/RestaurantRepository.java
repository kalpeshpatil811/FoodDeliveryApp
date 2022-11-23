package com.fooddeliveryapp.repository;

import com.fooddeliveryapp.entity.RestaurantOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantOwner, Long>{

}
