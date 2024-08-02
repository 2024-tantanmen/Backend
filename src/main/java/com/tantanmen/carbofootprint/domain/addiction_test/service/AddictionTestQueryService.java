package com.tantanmen.carbofootprint.domain.addiction_test.service;

import java.util.List;

import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.mypage.web.dto.MyPageResponseDto;

public interface AddictionTestQueryService {
	List<MyPageResponseDto.MyPageAddictionTestResponseDto> getAddictionTestList(Member member);
}
