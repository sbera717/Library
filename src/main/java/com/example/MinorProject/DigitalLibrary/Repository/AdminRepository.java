package com.example.MinorProject.DigitalLibrary.Repository;

import com.example.MinorProject.DigitalLibrary.Model.Admin;
import com.example.MinorProject.DigitalLibrary.Model.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {

    UserRecord findByName(String name);
}
