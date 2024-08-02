package com.tantanmen.carbofootprint.domain.addiction_test.convertor;

import com.tantanmen.carbofootprint.domain.addiction_test.entity.AddictionTest;
import com.tantanmen.carbofootprint.domain.addiction_test.web.dto.AddictionTestRequestDto;
import com.tantanmen.carbofootprint.domain.mypage.web.dto.MyPageResponseDto;
import com.tantanmen.carbofootprint.global.util.LocalDateTimeFormatter;

public class AddictionTestConvertor {

	/**
	 * 데이터 저장 요청 DTO -> entity 변환
	 */
	public static AddictionTest toAddictionTest(AddictionTestRequestDto.SaveAddictionTestRequestDto request){
		return AddictionTest.builder()
			.checkCount(request.getCheck_count())
			.build();
	}

	/**
	 * AddictionTest entity => mypage 응답 DTO 변환
	 */
	public static MyPageResponseDto.MyPageAddictionTestResponseDto toMyPageAddictionTestResponseDto(AddictionTest addictionTest){
		return MyPageResponseDto.MyPageAddictionTestResponseDto.builder()
			.date(LocalDateTimeFormatter.formatLocalDateTime(addictionTest.getCreatedAt()))
			.check_count(addictionTest.getCheckCount())
			.build();
	}
}
