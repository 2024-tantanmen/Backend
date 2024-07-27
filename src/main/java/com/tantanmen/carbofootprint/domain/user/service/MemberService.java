package com.tantanmen.carbofootprint.domain.user.service;

import com.tantanmen.carbofootprint.domain.user.web.dto.LoginRequestDto;
import com.tantanmen.carbofootprint.domain.user.web.dto.LoginResponseDto;
import com.tantanmen.carbofootprint.domain.user.web.dto.SignUpRequestDto;
import com.tantanmen.carbofootprint.domain.user.web.dto.SignUpResponseDto;
import com.tantanmen.carbofootprint.global.entity.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity<CustomApiResponse<SignUpResponseDto>> signUp(SignUpRequestDto.Request request);
    ResponseEntity<CustomApiResponse<LoginResponseDto>> login(LoginRequestDto.Request request);
}
