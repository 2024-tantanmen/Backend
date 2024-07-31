package com.tantanmen.carbofootprint.domain.member.web.controller;

import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.member.service.MemberService;
import com.tantanmen.carbofootprint.domain.member.web.dto.AttendanceResponseDto;
import com.tantanmen.carbofootprint.domain.member.web.dto.LoginRequestDto;
import com.tantanmen.carbofootprint.domain.member.web.dto.LoginResponseDto;
import com.tantanmen.carbofootprint.domain.member.web.dto.SignUpResponseDto;
import com.tantanmen.carbofootprint.domain.member.web.dto.SignUpRequestDto;
import com.tantanmen.carbofootprint.domain.recommend.web.dto.RecommendResponseDto;
import com.tantanmen.carbofootprint.global.annotation.LoginMember;
import com.tantanmen.carbofootprint.global.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "사용자 관련 API", description = "사용자 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // SignUp
    @Operation(summary = "회원가입 API", description = "회원가입 API")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "COMMON200",
            description = "요청 성공",
            content = {
                @Content(
                    schema = @Schema(
                        implementation = SignUpResponseDto.class
                    )
                )
            }
        )
    })
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
    @Operation(summary = "로그인 API", description = "로그인 API")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "COMMON200",
            description = "요청 성공",
            content = {
                @Content(
                    schema = @Schema(
                        implementation = LoginResponseDto.class
                    )
                )
            }
        )
    })
    @PostMapping("/login")
    public ApiResponse<LoginResponseDto> login(@RequestBody LoginRequestDto.Request request){
        String token = memberService.login(request);
        LoginResponseDto result = LoginResponseDto.builder()
            .token(token)
            .build();

        return ApiResponse.onSuccess(result);
    }

    //attendance
    @Operation(summary = "연속 출석 일수 요청 API", description = "연속 출석 일수 요청 API")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "COMMON200",
            description = "요청 성공",
            content = {
                @Content(
                    schema = @Schema(
                        implementation = AttendanceResponseDto.GetAttendanceResponseDto.class
                    )
                )
            }
        )
    })
    @GetMapping("/attendance")
    public ApiResponse<AttendanceResponseDto.GetAttendanceResponseDto> getAttendance(@Parameter(hidden = true) @LoginMember Member member){
        AttendanceResponseDto.GetAttendanceResponseDto result = AttendanceResponseDto.GetAttendanceResponseDto.builder()
            .attendance_streak_count(member.getAttendanceStreakCount())
            .build();
        return ApiResponse.onSuccess(result);
    }

}
