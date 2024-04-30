package com.netprodex.web.controller;

import com.netprodex.service.UserDetailServiceImpl;
import com.netprodex.web.dto.AuthCreateUserRequest;
import com.netprodex.web.dto.AuthLoginRequest;
import com.netprodex.web.dto.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "Endpoint for authentication")
public class AuthenticationController {

    private final UserDetailServiceImpl userDetailService;

    @Autowired
    public AuthenticationController(UserDetailServiceImpl userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Operation(summary = "Post create user", description = "Create user in BD and get token JWT")
    @PostMapping(value = "/signup", produces = "application/json")
    public ResponseEntity<AuthResponse> signup(@RequestBody @Valid AuthCreateUserRequest authCreateUser) {
        return new ResponseEntity<>(this.userDetailService.createUser(authCreateUser), HttpStatus.CREATED);
    }

    @Operation(summary = "Post login user", description = "Login user get JWT authenticate")
    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest) {
        return new ResponseEntity<>(this.userDetailService.loginUser(userRequest), HttpStatus.OK);
    }

}
