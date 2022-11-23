package com.fooddeliveryapp.service;

import com.fooddeliveryapp.entity.Admin;
import com.fooddeliveryapp.exception.AdminNotFoundException;
import com.fooddeliveryapp.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }
    @Override
    public List<Admin> deleteAdmin(Long adminId) throws AdminNotFoundException {
        try {
            adminRepository.deleteById(adminId);
            return adminRepository.findAll();
        } catch (Exception e) {
            throw new AdminNotFoundException("Id is not present, enter correct Id");
        }
    }

    @Override
    public Admin updateAdminById(Long adminId, Admin admin) {
        Admin admi = adminRepository.findById(adminId).get();
        admi.setAdminName(admi.getAdminUserName());
        admi.setAdminUserName(admin.getAdminUserName());
        admi.setAdminEmail(admin.getAdminEmail());
        admi.setPassword(admin.getPassword());
        Admin updatedAdmin = adminRepository.save(admi);
        return adminRepository.save(updatedAdmin);
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin getAdminById(Long adminId) throws AdminNotFoundException {
        try {
            Admin admin = adminRepository.findById(adminId).get();
            return admin;
        } catch (Exception e) {
            throw new AdminNotFoundException("Id is not present, enter correct Id");
        }
    }
}
