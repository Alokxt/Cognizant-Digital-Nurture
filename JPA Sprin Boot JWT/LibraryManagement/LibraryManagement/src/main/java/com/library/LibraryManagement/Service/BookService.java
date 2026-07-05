package com.library.LibraryManagement.Service;

import com.library.LibraryManagement.Entities.BookStore;
import com.library.LibraryManagement.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Optional<BookStore> findById(Long id){
        return this.bookRepository.findById(id);
    }



    public List<BookStore> getAllBooks(){
        return this.bookRepository.findAll();
    }
}
