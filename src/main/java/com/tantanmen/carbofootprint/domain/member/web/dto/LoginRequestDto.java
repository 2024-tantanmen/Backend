package com.tantanmen.carbofootprint.domain.member.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Schema(title = "LOGIN_REQ_01", description = "로그인 요청 DTO")
public class LoginRequestDto {
    @Data
    public static class Request {
        @Schema(description = "로그인 ID", example = "user1234")
        private String loginId;
        @Schema(description = "비밀번호", example = "password123")
        private String password;
    }
}
