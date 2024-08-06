package com.tantanmen.carbofootprint.domain.member.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Schema(title = "LOGIN_RES_01", description = "로그인 응답 DTO")
@Getter
@Builder
@AllArgsConstructor
public class LoginResponseDto {
    @Schema(description = "JWT 토큰 값", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdW5oYTEyMyIsImF1dGgiOlsiUk9MRV9NRU1CRVIiXSwiaWF0IjoxNzIyNDIwMTIzLCJleHAiOjE3MjI1MDY1MjN9.4U0Rr5VPyj16iDGH3ER9Vs2GMFyCdUDMamIUOYgd29k")
    private String token;
}
