package com.tantanmen.carbofootprint.domain.member.service;

import com.tantanmen.carbofootprint.domain.member.web.dto.LoginRequestDto;
import com.tantanmen.carbofootprint.domain.member.web.dto.LoginResponseDto;
import com.tantanmen.carbofootprint.domain.member.web.dto.SignUpRequestDto;
import com.tantanmen.carbofootprint.domain.member.web.dto.SignUpResponseDto;
import com.tantanmen.carbofootprint.global.entity.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity<CustomApiResponse<SignUpResponseDto>> signUp(SignUpRequestDto.Request request);
    ResponseEntity<CustomApiResponse<LoginResponseDto>> login(LoginRequestDto.Request request);
}
