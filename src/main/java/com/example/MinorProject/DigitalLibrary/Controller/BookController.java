package com.example.MinorProject.DigitalLibrary.Controller;

import com.example.MinorProject.DigitalLibrary.DTO.CreateBookRequest;
import com.example.MinorProject.DigitalLibrary.DTO.CreateBookResponse;
import com.example.MinorProject.DigitalLibrary.DTO.SearchBookRequest;
import com.example.MinorProject.DigitalLibrary.Model.Book;
import com.example.MinorProject.DigitalLibrary.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/book/createBook")
    public Book createBook(@RequestBody @Valid CreateBookRequest createBookRequest){
        return bookService.createABook(createBookRequest);

    }
    @GetMapping("/book/all")
    public List<Book> getAllBook(){
        return  bookService.getAllBookFromDB();
    }

    @DeleteMapping("/book/{id}")
    public CreateBookResponse deleteBook(@PathVariable ("id") int id){
        return bookService.deleteABook(id);
    }

    @GetMapping("/book/search")
    public  List<Book> searchByDifferentValue(@RequestBody @Valid SearchBookRequest searchBookRequest) throws Exception {
        return bookService.bookSearch(searchBookRequest);
    }


}
