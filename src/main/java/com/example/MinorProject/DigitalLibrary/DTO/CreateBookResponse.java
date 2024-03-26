package com.example.MinorProject.DigitalLibrary.DTO;

import com.example.MinorProject.DigitalLibrary.Model.Author;
import com.example.MinorProject.DigitalLibrary.Model.Book;
import com.example.MinorProject.DigitalLibrary.Model.Genre;
import com.example.MinorProject.DigitalLibrary.Model.Student;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookResponse {
    private int id;

    private  String name;

    private Genre genre;

    private  int pages;

    private Author my_author;

    private Student student;

    private Date createdOn;

    private Date updatedOn;

    public static CreateBookResponse from(Book b){
        return  CreateBookResponse.builder()
                .id(b.getId())
                .name(b.getName())
                .genre(b.getGenre())
                .pages(b.getPages())
                .my_author(b.getMy_author())
                .student(b.getStudent())
                .createdOn(b.getCreatedOn())
                .updatedOn(b.getUpdatedOn())
                .build();
    }

}