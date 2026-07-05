package com.library.LibraryManagement.Service;

import com.library.LibraryManagement.Entities.Users;
import com.library.LibraryManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUsersDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomUsersDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        Users user = userRepository.findByUsername(userName).orElseThrow(()-> new UsernameNotFoundException("User Not found: "+userName));
        return org.springframework.security.core.userdetails.User.builder().username(user.getUsername()).password(user.getPassword())
                .authorities(List.of(new SimpleGrantedAuthority(user.getRole().name())))
                .build();


    }
}
