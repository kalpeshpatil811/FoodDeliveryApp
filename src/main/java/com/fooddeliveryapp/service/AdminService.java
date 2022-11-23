package com.fooddeliveryapp.service;

import com.fooddeliveryapp.entity.Admin;
import com.fooddeliveryapp.exception.AdminNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AdminService {

    List<Admin> deleteAdmin(Long adminId) throws AdminNotFoundException;

    Admin updateAdminById(Long adminId, Admin customer);

    Admin updateAdmin(Admin admin);

    Admin getAdminById(Long adminId) throws AdminNotFoundException;

    List<Admin> getAllAdmins();

    Admin addAdmin(Admin admi);
}
