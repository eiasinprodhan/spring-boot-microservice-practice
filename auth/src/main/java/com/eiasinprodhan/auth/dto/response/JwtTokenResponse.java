package com.eiasinprodhan.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JwtTokenResponse {
    private String token;
    private String tokenType;
    private String validity;
}
