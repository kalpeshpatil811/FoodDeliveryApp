package com.fooddeliveryapp.service;


import com.fooddeliveryapp.entity.Admin;
import com.fooddeliveryapp.repository.AdminRepository;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class AdminServiceTest {

    @InjectMocks
    private AdminServiceImpl adminServiceImpl;

    @InjectMocks
    private Admin admin;

    @Mock
    private AdminRepository adminRepository;

    public void setUp() {

        admin.setAdminId(1L);
        admin.setAdminName("Ram");
        admin.setAdminUserName("ram");
        admin.setAdminEmail("ram@gmail.com");
        admin.setPassword("admin@12345");

    }

    @Test
    public void addAdminTest() {
        Mockito.doReturn(admin).when(adminRepository).save(Mockito.any());
        assertEquals(admin.getAdminId(), adminServiceImpl.addAdmin(admin).getAdminId());
        assertEquals(admin.getAdminName(), adminServiceImpl.addAdmin(admin).getAdminName());
        assertEquals(admin.getAdminUserName(), adminServiceImpl.addAdmin(admin).getAdminUserName());
        assertEquals(admin.getAdminEmail(), adminServiceImpl.addAdmin(admin).getAdminEmail());
        assertEquals(admin.getPassword(), adminServiceImpl.addAdmin(admin).getPassword());
    }

    @Test
    public void updateAdminTest() {
        Mockito.doReturn(admin).when(adminRepository).save(Mockito.any());
        assertEquals(admin.getAdminId(), adminServiceImpl.updateAdmin(admin).getAdminId());
        assertEquals(admin.getAdminName(), adminServiceImpl.updateAdmin(admin).getAdminName());
        assertEquals(admin.getAdminUserName(), adminServiceImpl.updateAdmin(admin).getAdminUserName());
        assertEquals(admin.getAdminEmail(), adminServiceImpl.updateAdmin(admin).getAdminEmail());
        assertEquals(admin.getPassword(), adminServiceImpl.updateAdmin(admin).getPassword());
    }
}
