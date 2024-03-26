package com.example.MinorProject.DigitalLibrary.DTO;

import com.example.MinorProject.DigitalLibrary.Model.Student;
import com.example.MinorProject.DigitalLibrary.Model.UserRecord;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@Valid
public class CreateStudentRequest {

    @NotBlank
    private  String name;

    @NotBlank
    private String contact;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public Student to(){
        return Student.builder()
                .name(this.name)
                .contact(this.contact)
                .userRecord(
                        UserRecord.builder()
                                .username(this.username)
                                .password(this.password)
                                .build()
                )
                .validity(new Date(System.currentTimeMillis() + 31536000000L)) // calling the new date for updating everytime
                .build();
    }
}

