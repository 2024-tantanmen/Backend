package com.tantanmen.carbofootprint.domain.user.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpResponseDto {
    private String token;
}
