package com.foysal.jwt.controllers;

import com.foysal.jwt.dto.AuthRequest;
import com.foysal.jwt.dto.AuthResponse;
import com.foysal.jwt.dto.RefreshRequest;
import com.foysal.jwt.services.impl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshRequest request) {
        return ResponseEntity.ok(authService.refreshToken(request));
    }
}
