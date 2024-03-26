package com.example.MinorProject.DigitalLibrary.Controller;

import com.example.MinorProject.DigitalLibrary.DTO.CreateAdminRequest;
import com.example.MinorProject.DigitalLibrary.Model.Admin;
import com.example.MinorProject.DigitalLibrary.Service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/createAdmin")
    public Admin createAdmin(@RequestBody @Valid CreateAdminRequest createAdminRequest){
        return adminService.createAAdmin(createAdminRequest);
    }



}
