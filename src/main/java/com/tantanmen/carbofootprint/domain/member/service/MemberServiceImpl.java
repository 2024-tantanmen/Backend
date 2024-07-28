package com.tantanmen.carbofootprint.domain.member.service;

import com.tantanmen.carbofootprint.domain.member.entity.Authority;
import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.member.enums.AuthorityType;
import com.tantanmen.carbofootprint.domain.member.exception.MemberNotExistException;
import com.tantanmen.carbofootprint.domain.member.repository.MemberRepository;
import com.tantanmen.carbofootprint.domain.member.web.dto.LoginRequestDto;
import com.tantanmen.carbofootprint.domain.member.web.dto.SignUpRequestDto;
import com.tantanmen.carbofootprint.global.enums.statuscode.ErrorStatus;
import com.tantanmen.carbofootprint.global.exception.GeneralException;
import com.tantanmen.carbofootprint.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    // SignUp
    @Override
    public Member signUp(SignUpRequestDto.Request request) {

        log.info("SignUp loginId : {}", request.getLoginId());
        log.info("SignUp password : {}", request.getPassword());

        // 이미 존재하는 Login Id인 경우 예외
        if(memberRepository.existsByLoginId(request.getLoginId())) {
            throw new GeneralException(ErrorStatus._EXIST_LOGIN_ID);
        }

        Member member = Member.builder()
                .loginId(request.getLoginId())
                .password(passwordEncoder.encode(request.getPassword()))
                // TODO 사용자 닉네임 임시적으로 login id로 적용
                .username(request.getLoginId())
                .authorityList(new ArrayList<>())
                .build();

        Authority authority = Authority.builder()
                .type(AuthorityType.ROLE_MEMBER)
                .build();

        member.addAuthority(authority);

        memberRepository.save(member);

        return member;
    }

    // Login
    @Override
    public String login(LoginRequestDto.Request request) {

        log.info("Login loginId : {}", request.getLoginId());
        log.info("Login password : {}", request.getPassword());

        // LoginId에 맞는 사용자가 존재하지 않는 경우
        Member member = memberRepository.findByLoginId(request.getLoginId()).orElseThrow(() -> new MemberNotExistException());

        // 비밀번호가 올바르지 않은 경우
        if(!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new GeneralException(ErrorStatus._INVALID_LOGINID_OR_PASSWORD);
        }

        List<Authority> authorities = member.getAuthorityList();

        // 토큰 생성
        String token = jwtTokenProvider.createToken(member.getLoginId(), authorities);

        return token;
    }


}
