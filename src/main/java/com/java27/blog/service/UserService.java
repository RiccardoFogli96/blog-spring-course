package com.java27.blog.service;

import com.java27.blog.dto.UserDTO;
import com.java27.blog.entity.User;
import com.java27.blog.mapper.UserMapper;
import com.java27.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Page<UserDTO> getAllUsers(int page, int quantity){
        Pageable pageable = PageRequest.of(page,quantity);
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(userMapper::userDTO);
    }

    public User getUserByEmail(String username) throws Exception{
        return userRepository.findByEmail(username).orElseThrow(() -> new Exception ("No user with email " + username));
    }
}
