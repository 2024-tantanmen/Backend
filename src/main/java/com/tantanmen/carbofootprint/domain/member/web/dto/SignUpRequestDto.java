package com.tantanmen.carbofootprint.domain.member.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Schema(title = "SIGNUP_REQ_DTO", description = "회원가입 요청 DTO")
public class SignUpRequestDto {

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {

        @Schema(description = "회원가입 할 로그인 ID", example = "user1234")
        // @Pattern(regexp = "^[a-zA-Z0-9]{5,15}$", message = "아이디는 영문 또는 숫자로 5~15자여야 합니다.")
        @NotEmpty(message = "아이디가 필요합니다.")
        private String loginId;

        @Schema(description = "회원가입 할 비밀번호", example = "password123")
        // @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "비밀번호는 최소 하나의 문자 및 하나의 숫자, 특수문자를 포함하여 8자 이상이어야 합니다.")
        @NotEmpty(message = "비밀번호가 필요합니다.")
        private String password;

    }
}
