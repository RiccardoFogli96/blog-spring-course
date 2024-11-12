package com.java27.blog.controller;

import com.java27.blog.dto.AuthenticationResponse;
import com.java27.blog.dto.LoginRequest;
import com.java27.blog.dto.RegisterRequest;
import com.java27.blog.dto.UserDTO;
import com.java27.blog.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register-user")
    public ResponseEntity<UserDTO> registerUser(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) throws Exception {
        return ResponseEntity.ok(authenticationService.login(request));
    }

}
