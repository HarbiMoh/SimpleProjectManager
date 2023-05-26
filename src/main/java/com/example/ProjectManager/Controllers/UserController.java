package com.example.ProjectManager.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjectManager.Request.UserLoginRequest;
import com.example.ProjectManager.Request.UserSignupRequest;
import com.example.ProjectManager.Response.UserResponse;
import com.example.ProjectManager.Service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("api/v1/auth")
public class UserController {
    
    private final UserService userService;

    @PostMapping("signup")
    public UserResponse signup(@Valid @RequestBody UserSignupRequest requset){
        return userService.signup(requset);
    }

    @PostMapping("login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody UserLoginRequest requset){
        return userService.login(requset);
    }
}
