package com.java27.blog.controller;

import com.java27.blog.dto.UserDTO;
import com.java27.blog.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/users")
    public ResponseEntity<Page<UserDTO>> getAllUsers(@AuthenticationPrincipal UserDetails userDetails,
                                               @RequestParam int page,
                                               @RequestParam int quantity)throws Exception{

        Page<UserDTO> userDTOS = dashboardService.getAllUsers(userDetails,page,quantity);
        return ResponseEntity.status(HttpStatus.FOUND).body(userDTOS);
    }
}
