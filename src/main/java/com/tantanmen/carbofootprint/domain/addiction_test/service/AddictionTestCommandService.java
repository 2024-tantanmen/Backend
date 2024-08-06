package com.tantanmen.carbofootprint.domain.addiction_test.service;

import com.tantanmen.carbofootprint.domain.addiction_test.entity.AddictionTest;
import com.tantanmen.carbofootprint.domain.addiction_test.web.dto.AddictionTestRequestDto;
import com.tantanmen.carbofootprint.domain.member.entity.Member;

public interface AddictionTestCommandService {
	AddictionTest saveResult(AddictionTestRequestDto.SaveAddictionTestRequestDto request, Member member);
}
