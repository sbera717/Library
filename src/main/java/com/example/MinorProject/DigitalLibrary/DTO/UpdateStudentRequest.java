package com.example.MinorProject.DigitalLibrary.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Valid
public class UpdateStudentRequest {
    @NotBlank
    private  String name;

    @NotBlank
    private String contact;

//    private Date validity;
//
//    public void setValidity(){
//        validity = new Date((System.currentTimeMillis() + 31536000000l));
//    }



}