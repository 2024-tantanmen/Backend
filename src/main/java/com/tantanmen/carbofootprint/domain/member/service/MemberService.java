package com.tantanmen.carbofootprint.domain.member.service;

import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.member.web.dto.LoginRequestDto;
import com.tantanmen.carbofootprint.domain.member.web.dto.SignUpRequestDto;

public interface MemberService {
    Member signUp(SignUpRequestDto.Request request);
    String login(LoginRequestDto.Request request);
}
