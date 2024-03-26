package com.example.MinorProject.DigitalLibrary.Repository;

import com.example.MinorProject.DigitalLibrary.Model.Book;
import com.example.MinorProject.DigitalLibrary.Model.Genre;
import com.example.MinorProject.DigitalLibrary.Model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("select b from Book b where b.name =:name and b.student is null")
    List<Book> findByNameAndStudentIsNull(String name);

    List<Book> findByName(String name);

    List<Book> findByGenre(Genre genre);


    @Modifying // for DML support
    @Transactional // for updating any data
    @Query("update Book b set b.student = ?2 where b.id = ?1 and b.student is null")
    void assignBookToStudent(int bookId, Student student);

    @Modifying // for DML support
    @Transactional // for updating any data
    @Query("update Book b set b.student = null where b.id = ?1")
    void unassignBookToStudent(int bookId);
}