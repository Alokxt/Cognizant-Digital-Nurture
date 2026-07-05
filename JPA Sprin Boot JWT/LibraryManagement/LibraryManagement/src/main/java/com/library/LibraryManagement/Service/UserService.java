package com.library.LibraryManagement.Service;

import com.library.LibraryManagement.Entities.BookStore;
import com.library.LibraryManagement.Entities.Role;
import com.library.LibraryManagement.Entities.Users;
import com.library.LibraryManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean registerUser(String userName,String password){
        if(userRepository.findByUsername(userName).isPresent()){
            return false;
        }
        Users user = new Users();
        user.setUsername(userName);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
        return true;
    }

    public Users getUserByUserName(String userName){
        return userRepository.findByUsername(userName).orElseThrow();
    }

    public List<BookStore> addBook(String userName,BookStore book){
        Users user = userRepository.findByUsername(userName).orElseThrow();
        user.addBook(book);
        userRepository.save(user);
        return user.getBooks();
    }

    public List<BookStore> getMyBooks(String userName){
        Users user  = userRepository.findByUsername(userName).orElseThrow();
        return user.getBooks();
    }

    public BookStore getBookByTitle(String userName,String title){
        Users  user = userRepository.findByUsername(userName).orElseThrow();
        for(BookStore book:user.getBooks()){
            if(book.getTitle().equals(title)){
                return book;
            }
        }
        return null;
    }



}
