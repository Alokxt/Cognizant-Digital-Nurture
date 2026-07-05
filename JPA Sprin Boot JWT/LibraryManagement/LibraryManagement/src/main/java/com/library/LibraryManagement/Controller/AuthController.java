package com.library.LibraryManagement.Controller;

import com.library.LibraryManagement.Dto.LoginRequest;
import com.library.LibraryManagement.Dto.RegistrationRequest;
import com.library.LibraryManagement.Repository.UserRepository;
import com.library.LibraryManagement.Service.UserService;
import com.library.LibraryManagement.config.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtil;

    @Autowired
    public AuthController(UserService userService, AuthenticationManager authenticationManager, JWTUtils jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest){
        try{
            System.out.println(registrationRequest.getUsername());
            boolean ans = userService.registerUser(registrationRequest.getUsername(),registrationRequest.getPassword());
            if(!ans){
                return new ResponseEntity<>("User Already Exist", HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>("User Registered Successfully",HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
            );

            String token = jwtUtil.generateToken(request.getUserName());

            return ResponseEntity.ok(Map.of("token", token));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }



}
