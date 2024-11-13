package com.java27.blog.controller;

import com.java27.blog.dto.AuthenticationResponse;
import com.java27.blog.dto.LoginRequest;
import com.java27.blog.dto.RegisterRequest;
import com.java27.blog.dto.UserDTO;
import com.java27.blog.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;



    @PostMapping("/register-user")
    public ResponseEntity<UserDTO> registerUser(@RequestBody RegisterRequest request) throws IOException {
        return ResponseEntity.ok(authenticationService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) throws Exception {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PatchMapping("/confirm-email")
    public ResponseEntity<Void> confirmUserEmail(@RequestParam ("token") String token){
        authenticationService.confirmUserEmail(token);
        return ResponseEntity.ok().build();
    }

}
