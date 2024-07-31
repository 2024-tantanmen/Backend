package com.tantanmen.carbofootprint.domain.member.web.controller;

import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.member.service.MemberService;
import com.tantanmen.carbofootprint.domain.member.web.dto.AttendanceResponseDto;
import com.tantanmen.carbofootprint.domain.member.web.dto.LoginRequestDto;
import com.tantanmen.carbofootprint.domain.member.web.dto.LoginResponseDto;
import com.tantanmen.carbofootprint.domain.member.web.dto.SignUpResponseDto;
import com.tantanmen.carbofootprint.domain.member.web.dto.SignUpRequestDto;
import com.tantanmen.carbofootprint.global.annotation.LoginMember;
import com.tantanmen.carbofootprint.global.response.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // SignUp
    @PostMapping("/signup")
    public ApiResponse<SignUpResponseDto> signUp(@Valid @RequestBody SignUpRequestDto.Request request) {

        Member member = memberService.signUp(request);

        // JWT 토큰 => 저장된 사용자 고유 번호로 변경
        SignUpResponseDto result = SignUpResponseDto.builder()
            .member_id(member.getId())
            .build();
        return ApiResponse.onSuccess(result);
    }

    // Login
    @PostMapping("/login")
    public ApiResponse<LoginResponseDto> login(@RequestBody LoginRequestDto.Request request){
        String token = memberService.login(request);
        LoginResponseDto result = LoginResponseDto.builder()
            .token(token)
            .build();

        return ApiResponse.onSuccess(result);
    }

    //attendance
    @GetMapping("/attendance")
    public ApiResponse<AttendanceResponseDto.GetAttendanceResponseDto> getAttendance(@LoginMember Member member){
        AttendanceResponseDto.GetAttendanceResponseDto result = AttendanceResponseDto.GetAttendanceResponseDto.builder()
            .attendance_streak_count(member.getAttendanceStreakCount())
            .build();
        return ApiResponse.onSuccess(result);
    }

}
