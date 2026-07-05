package com.library.LibraryManagement.Controller;

import com.library.LibraryManagement.Entities.BookStore;
import com.library.LibraryManagement.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    ResponseEntity<?> getBooks(){
        List<BookStore>   ans = bookService.getAllBooks();
        return new ResponseEntity<>(ans,HttpStatus.OK);
    }

    @GetMapping("/id/{book_id}")
    ResponseEntity<?> getBookById(@PathVariable Long book_id){
        Optional<BookStore> book = this.bookService.findById(book_id);
        return new ResponseEntity<>(book,HttpStatus.OK);
    }
}
