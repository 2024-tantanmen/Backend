package com.tantanmen.carbofootprint.domain.member.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(title = "SIGNUP_RES_DTO", description = "회원가입 응답 DTO")
public class SignUpResponseDto {
    @Schema(description = "회원가입 사용자의 고유 번호", example = "12")
    private Long member_id;
}
