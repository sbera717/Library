package com.example.MinorProject.DigitalLibrary.Repository;

import com.example.MinorProject.DigitalLibrary.Model.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserRecord,Integer> {

    UserRecord findByUsername(String user);
}
