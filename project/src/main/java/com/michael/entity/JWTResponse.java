package com.michael.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JWTResponse {
    private  final String jwtToken;
}
