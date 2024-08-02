package com.tantanmen.carbofootprint.domain.addiction_test.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tantanmen.carbofootprint.domain.addiction_test.convertor.AddictionTestConvertor;
import com.tantanmen.carbofootprint.domain.addiction_test.entity.AddictionTest;
import com.tantanmen.carbofootprint.domain.addiction_test.repository.AddictionTestRepository;
import com.tantanmen.carbofootprint.domain.addiction_test.web.dto.AddictionTestRequestDto;
import com.tantanmen.carbofootprint.domain.member.entity.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AddictionTestCommandServiceImpl implements AddictionTestCommandService{
	private final AddictionTestRepository addictionTestRepository;

	/**
	 * 탄수 중독 테스트 결과 데이터 저장
	 */
	@Override
	public AddictionTest saveResult(AddictionTestRequestDto.SaveAddictionTestRequestDto request, Member member){
		AddictionTest addictionTest = AddictionTestConvertor.toAddictionTest(request);
		addictionTestRepository.save(addictionTest);

		member.addAddictionTest(addictionTest);
		return addictionTest;
	}
}
