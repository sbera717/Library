package com.example.MinorProject.DigitalLibrary.DTO;

import com.example.MinorProject.DigitalLibrary.Model.Student;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateStudentResponse implements Serializable {


    private int id;

    private  String name;

    private String contact;
    private Date createdOn;
    private  Date updatedOn;

    private Date validity;

    public CreateStudentResponse (Student student){
        this.id = student.getId();
        this.name = student.getName();
        this.contact = student.getContact();
        this.createdOn = student.getCreatedOn();
        this.updatedOn = student.getUpdatedOn();
        this.validity = student.getValidity();
    }

}
