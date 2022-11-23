package com.fooddeliveryapp.controller;

import com.fooddeliveryapp.entity.Admin;
import com.fooddeliveryapp.exception.AdminNotFoundException;
import com.fooddeliveryapp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/v1")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/admin")
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin cust){
        return new ResponseEntity<Admin>(adminService.addAdmin(cust), HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<List<Admin>> getAllAdmins(){
        return new ResponseEntity<List<Admin>>(adminService.getAllAdmins(),HttpStatus.OK);
    }

    @GetMapping("admin/{adminId}")
    public ResponseEntity<Admin> getAdminById(@PathVariable("adminId") Long customerId)
            throws AdminNotFoundException {
        return new ResponseEntity<Admin>(adminService.getAdminById(customerId), HttpStatus.OK);
    }

    @PutMapping("admin")
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) {
        return new ResponseEntity<Admin>(adminService.updateAdmin(admin), HttpStatus.OK);
    }

    @PutMapping("admin/{adminId}")
    public ResponseEntity<Admin> updateAdminById(@PathVariable("adminId") Long adminId, @RequestBody Admin admin) {
        return new ResponseEntity<Admin>(adminService.updateAdminById(adminId,admin), HttpStatus.OK);
    }

    @DeleteMapping("admin/{adminId}")
    public ResponseEntity<List<Admin>> deleteadmin(@PathVariable("adminId") Long adminId)
            throws AdminNotFoundException {
        List<Admin> adminList = adminService.deleteAdmin(adminId);
        return new ResponseEntity<List<Admin>>(adminList, HttpStatus.OK);
    }

}
