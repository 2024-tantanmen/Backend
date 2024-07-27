package com.tantanmen.carbofootprint.domain.member.service;

import com.tantanmen.carbofootprint.domain.member.entity.Authority;
import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.member.enums.MemberRoles;
import com.tantanmen.carbofootprint.domain.member.repository.MemberRepository;
import com.tantanmen.carbofootprint.domain.member.web.dto.LoginRequestDto;
import com.tantanmen.carbofootprint.domain.member.web.dto.LoginResponseDto;
import com.tantanmen.carbofootprint.domain.member.web.dto.SignUpResponseDto;
import com.tantanmen.carbofootprint.domain.member.web.dto.SignUpRequestDto;
import com.tantanmen.carbofootprint.global.entity.response.CustomApiResponse;
import com.tantanmen.carbofootprint.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    // SignUp
    @Override
    public ResponseEntity<CustomApiResponse<SignUpResponseDto>> signUp(SignUpRequestDto.Request request) {

        log.info("SignUp loginId : {}", request.getLoginId());
        log.info("SignUp password : {}", request.getPassword());

        if(memberRepository.existsByLoginId(request.getLoginId())) {
            CustomApiResponse<SignUpResponseDto> existsLoginId = CustomApiResponse.createFailWithoutData(HttpStatus.METHOD_NOT_ALLOWED.value(), "이미 존재하는 아이디 입니다.");

            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(existsLoginId);
        }

        Member member = Member.builder()
                .loginId(request.getLoginId())
                .password(passwordEncoder.encode(request.getPassword()))
                .authorities(new ArrayList<>())
                .build();

        Authority authority = Authority.builder()
                .authorityType(MemberRoles.ADMIN)
                .build();

        member.addRole(authority);

        memberRepository.save(member);

        List<Authority> authorities = member.getAuthorities().stream()
                .map(Function.identity())
                .collect(Collectors.toList());

        String token = jwtTokenProvider.createToken(member.getLoginId(), authorities);

        SignUpResponseDto responseDto = SignUpResponseDto.builder()
                .token(token)
                .build();

        CustomApiResponse<SignUpResponseDto> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), responseDto, "회원가입에 성공하였습니다.");

        return ResponseEntity.ok(response);
    }

    // Login
    @Override
    public ResponseEntity<CustomApiResponse<LoginResponseDto>> login(LoginRequestDto.Request request) {

        log.info("Login loginId : {}", request.getLoginId());
        log.info("Login password : {}", request.getPassword());

        if(!memberRepository.existsByLoginId(request.getLoginId())) {
            CustomApiResponse<LoginResponseDto> loginIdError = CustomApiResponse.createFailWithoutData(HttpStatus.METHOD_NOT_ALLOWED.value(), "잘못된 아이디입니다.");

            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(loginIdError);
        }

        Member member = memberRepository.findByLoginId(request.getLoginId()).orElseThrow(RuntimeException::new);

        if(!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            CustomApiResponse<LoginResponseDto> passwordError = CustomApiResponse.createFailWithoutData(HttpStatus.METHOD_NOT_ALLOWED.value(), "잘못된 비밀번호입니다.");

            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(passwordError);
        }

        List<Authority> authorities = member.getAuthorities().stream()
                .map(Function.identity())
                .collect(Collectors.toList());

        String token = jwtTokenProvider.createToken(member.getLoginId(), authorities);

        LoginResponseDto responseDto = LoginResponseDto.builder()
                .token(token)
                .build();


        CustomApiResponse<LoginResponseDto> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), responseDto, "로그인에 성공하였습니다.");

        return ResponseEntity.ok(response);
    }


}
