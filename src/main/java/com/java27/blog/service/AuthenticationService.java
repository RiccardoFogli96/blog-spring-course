package com.java27.blog.service;

import com.java27.blog.dto.AuthenticationResponse;
import com.java27.blog.dto.LoginRequest;
import com.java27.blog.dto.RegisterRequest;
import com.java27.blog.mapper.UserMapper;
import com.java27.blog.dto.UserDTO;
import com.java27.blog.entity.User;
import com.java27.blog.model.UserStatus;
import com.java27.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final EmailService emailService;

    @Value("${send-email.path}")
    String link;

    public UserDTO registerUser(RegisterRequest request) throws IOException {
        User user = userMapper.toUserFromRegisterRequest(request);
        userRepository.save(user);
      //  emailService.sendMail(user.getEmail(), link + "?token=" + user.getUuid());
        return userMapper.userDTO(user);
    }

    public AuthenticationResponse login(LoginRequest request) throws Exception {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new Exception("No user found with email: " + request.getEmail()));
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public void confirmUserEmail(String token) {
        User user = userRepository.findByUuid(token).orElseThrow(()-> new NoSuchElementException("There is no User with uuid " + token));
        user.setUserStatus(UserStatus.ACTIVE);
        userRepository.save(user);

    }
}
