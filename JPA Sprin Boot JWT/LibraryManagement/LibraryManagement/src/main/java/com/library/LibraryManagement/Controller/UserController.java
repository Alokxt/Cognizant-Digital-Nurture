package com.library.LibraryManagement.Controller;

import com.library.LibraryManagement.Entities.BookStore;
import com.library.LibraryManagement.Entities.Users;
import com.library.LibraryManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/myBooks")
    ResponseEntity<?> getBooks(Authentication authentication){
        String nam = authentication.getName();

        List<BookStore> ans = userService.getMyBooks(nam);
        return new ResponseEntity<>(ans, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> addBook(@RequestBody BookStore book,Authentication authentication){
        String nam = authentication.getName();
        List<BookStore> ans = userService.addBook(nam,book);
        return new ResponseEntity<>(ans,HttpStatus.OK);
    }


}
