package com.foysal.jwt.services.impl;

import com.foysal.jwt.dto.AuthRequest;
import com.foysal.jwt.dto.AuthResponse;
import com.foysal.jwt.dto.RefreshRequest;
import com.foysal.jwt.entity.RefreshToken;
import com.foysal.jwt.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    private final long refreshTokenDuration = 7 * 24 * 60 * 60 * 1000; // 7 days

    public AuthResponse login(AuthRequest request) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                )
        );

        UserDetails user = (UserDetails) auth.getPrincipal();

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        refreshTokenRepository.save(
                RefreshToken.builder()
                        .token(refreshToken)
                        .username(user.getUsername())
                        .expiryDate(Instant.now().plusMillis(refreshTokenDuration))
                        .build()
        );

        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse refreshToken(RefreshRequest request) {
        String refreshToken = request.getRefreshToken();

        RefreshToken tokenEntity = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (tokenEntity.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(tokenEntity);
            throw new RuntimeException("Refresh token expired");
        }

        UserDetails user = userDetailsService.loadUserByUsername(tokenEntity.getUsername());
        String newAccessToken = jwtService.generateAccessToken(user);

        // Optional: rotate refresh token
        String newRefreshToken = jwtService.generateRefreshToken(user);
        tokenEntity.setToken(newRefreshToken);
        tokenEntity.setExpiryDate(Instant.now().plusMillis(refreshTokenDuration));
        refreshTokenRepository.save(tokenEntity);

        return new AuthResponse(newAccessToken, newRefreshToken);
    }
}

