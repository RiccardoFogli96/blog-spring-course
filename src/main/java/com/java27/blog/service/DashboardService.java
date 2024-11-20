package com.java27.blog.service;

import com.java27.blog.dto.UserDTO;
import com.java27.blog.entity.User;
import com.java27.blog.model.TypeUser;
import com.java27.blog.utils.RoleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final UserService userService;

    public Page<UserDTO> getAllUsers(UserDetails userDetails, int page, int quantity)throws Exception{
        User user = (User) userDetails;
        RoleUtils.isPermit(user, List.of(TypeUser.ADMIN));
        return userService.getAllUsers(page,quantity);
    }
}
