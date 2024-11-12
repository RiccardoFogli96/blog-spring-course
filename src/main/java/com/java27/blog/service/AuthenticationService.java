package com.java27.blog.service;

import com.java27.blog.config.AuthenticationResponse;
import com.java27.blog.config.LoginRequest;
import com.java27.blog.config.RegisterRequest;
import com.java27.blog.config.utils.UserMapper;
import com.java27.blog.dto.UserDTO;
import com.java27.blog.entity.User;
import com.java27.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserDTO registerUser(RegisterRequest request) {
        User user = userMapper.toUserFromRegisterRequest(request);
        userRepository.save(user);
        return userMapper.userDTO(user);
    }

    public AuthenticationResponse login(LoginRequest request) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new Exception("No user found with email: " + request.getEmail()));
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

}
