package com.tantanmen.carbofootprint.domain.addiction_test.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tantanmen.carbofootprint.domain.addiction_test.convertor.AddictionTestConvertor;
import com.tantanmen.carbofootprint.domain.addiction_test.repository.AddictionTestRepository;
import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.mypage.web.dto.MyPageResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AddictionTestQueryServiceImpl implements AddictionTestQueryService{
	private final AddictionTestRepository addictionTestRepository;

	/**
	 * 탄수화물 중독 테스트 결과 데이터 반환
	 */
	@Override
	public List<MyPageResponseDto.MyPageAddictionTestResponseDto> getAddictionTestList(Member member){
		return addictionTestRepository.findByMemberId(member.getId()).stream().map(AddictionTestConvertor::toMyPageAddictionTestResponseDto).collect(
			Collectors.toList());
	}


}
