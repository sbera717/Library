package com.example.MinorProject.DigitalLibrary.Controller;

import com.example.MinorProject.DigitalLibrary.DTO.CreateStudentRequest;
import com.example.MinorProject.DigitalLibrary.DTO.CreateStudentResponse;
import com.example.MinorProject.DigitalLibrary.DTO.UpdateStudentRequest;
import com.example.MinorProject.DigitalLibrary.Model.Student;
import com.example.MinorProject.DigitalLibrary.Model.UserRecord;
import com.example.MinorProject.DigitalLibrary.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/student/createStudent")
    public Student createStudent(@RequestBody @Valid CreateStudentRequest createStudentRequest){
        return studentService.createAStudent(createStudentRequest);
    }
    //because this api is vulnerable if someone get student id he can get all information
    // creating another api with different path
    @GetMapping("/student/{ID}") // This api can be invoked by admin only
    public Student getStudentAdmin(@PathVariable ("ID") int id){
        return studentService.getAStudent(id);

    }
    @GetMapping("/student/details") // here id won't be taken because if you login as  a student you won't enter your id
    public CreateStudentResponse getStudentUser(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UserRecord userRecord = (UserRecord) authentication.getPrincipal();
        int id = userRecord.getStudent().getId();
        return studentService.getAStudentUsingCache(id);

    }
    @PutMapping("/student/updateAccount")
    public Student updateStudent(@RequestBody UpdateStudentRequest updateStudentRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserRecord userRecord = (UserRecord) authentication.getPrincipal();
        int id = userRecord.getStudent().getId();
        return studentService.updateAStudent(id,updateStudentRequest);
    }

    // to do in endpoint
    @DeleteMapping("/student/deleteAccount")
    // to do student fetching issue lazy initlization
    // can create a response using dto if lazy won't solved means
    public String DeleteStudent(){
        UserRecord userRecord = (UserRecord) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int id = userRecord.getStudent().getId();
        return "Deleted Successfully";
    }






}
