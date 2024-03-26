package com.example.MinorProject.DigitalLibrary.Service;

import com.example.MinorProject.DigitalLibrary.DTO.CreateStudentRequest;
import com.example.MinorProject.DigitalLibrary.DTO.CreateStudentResponse;
import com.example.MinorProject.DigitalLibrary.DTO.UpdateStudentRequest;
import com.example.MinorProject.DigitalLibrary.Model.Student;
import com.example.MinorProject.DigitalLibrary.Model.UserRecord;
import com.example.MinorProject.DigitalLibrary.Repository.StudentCacheRepository;
import com.example.MinorProject.DigitalLibrary.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    StudentCacheRepository studentCacheRepository;

    @Value("${student.authorities}")
    String authorities;

    public Student createAStudent(CreateStudentRequest createStudentRequest) {
        Student student = createStudentRequest.to();
        UserRecord userRecord = student.getUserRecord();
        userRecord.setPassword(passwordEncoder.encode(userRecord.getPassword()));
        // this is working because userService is in security config not student service so it won't make a cycle
        userRecord.setAuthorities(authorities);
        userRecord = userService.save(userRecord);
        student.setUserRecord(userRecord);
        return studentRepository.save(student);
    }

    public Student getAStudent(int id)
    {
        return studentRepository.findById(id).orElse(null);
    }

    public CreateStudentResponse getAStudentUsingCache(int id){

        CreateStudentResponse student = studentCacheRepository.get(id);
        if(student == null){
            Student studentExisting = studentRepository.findById(id).orElse(null);
            if(studentExisting != null){
                student = new CreateStudentResponse(studentExisting);
                studentCacheRepository.set(student);
            }else{
                throw  new NoSuchElementException("No student found with ID:"  + id);
            }
        }
        return student;

    }

    //To Do
    public Student updateAStudent(int id , UpdateStudentRequest updateStudentRequest){
        return null;
    }


    public Student deleteAStudent(int id) {
        Student student = getAStudent(id);
        studentRepository.deleteById(id);
        return  student;
    }
}
