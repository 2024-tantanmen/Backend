package com.tantanmen.carbofootprint.domain.member.web.controller;

import com.tantanmen.carbofootprint.domain.member.repository.MemberRepository;
import com.tantanmen.carbofootprint.domain.member.service.MemberService;
import com.tantanmen.carbofootprint.domain.member.web.dto.LoginRequestDto;
import com.tantanmen.carbofootprint.domain.member.web.dto.LoginResponseDto;
import com.tantanmen.carbofootprint.domain.member.web.dto.SignUpResponseDto;
import com.tantanmen.carbofootprint.domain.member.web.dto.SignUpRequestDto;
import com.tantanmen.carbofootprint.global.entity.response.CustomApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    // SignUp
    @PostMapping("/signUp")
    public ResponseEntity<CustomApiResponse<SignUpResponseDto>> signUp(@Valid @RequestBody SignUpRequestDto.Request request) {

        ResponseEntity<CustomApiResponse<SignUpResponseDto>> response = memberService.signUp(request);

        return response;
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<CustomApiResponse<LoginResponseDto>> login(@RequestBody LoginRequestDto.Request request){

        ResponseEntity<CustomApiResponse<LoginResponseDto>> response = memberService.login(request);

        return response;
    }

}
