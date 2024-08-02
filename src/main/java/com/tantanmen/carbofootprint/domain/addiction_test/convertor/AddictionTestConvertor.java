package com.tantanmen.carbofootprint.domain.addiction_test.convertor;

import com.tantanmen.carbofootprint.domain.addiction_test.entity.AddictionTest;
import com.tantanmen.carbofootprint.domain.addiction_test.web.dto.AddictionTestRequestDto;

public class AddictionTestConvertor {

	/**
	 * 데이터 저장 요청 DTO -> entity 변환
	 */
	public static AddictionTest toAddictionTest(AddictionTestRequestDto.SaveAddictionTestRequestDto request){
		return AddictionTest.builder()
			.checkCount(request.getCheck_count())
			.build();
	}
}
