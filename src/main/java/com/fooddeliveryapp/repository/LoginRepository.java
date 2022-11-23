package com.fooddeliveryapp.repository;

import com.fooddeliveryapp.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login,String>{

}
