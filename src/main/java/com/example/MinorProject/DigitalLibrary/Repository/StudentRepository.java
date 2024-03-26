package com.example.MinorProject.DigitalLibrary.Repository;

import com.example.MinorProject.DigitalLibrary.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface StudentRepository extends JpaRepository<Student,Integer> {
}
