package com.foysal.jwt.dto;

import lombok.Data;

@Data
public class RefreshRequest {
    private String refreshToken;
}
