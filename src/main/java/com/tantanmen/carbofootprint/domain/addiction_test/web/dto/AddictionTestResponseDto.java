package com.tantanmen.carbofootprint.domain.addiction_test.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(title = "ADDICTION_RES_01", description = "탄수 중독 테스트 응답 DTO")
public class AddictionTestResponseDto {
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class SaveAddictionTestResponseDto{
		@Schema(description = "저장된 중독 테스트 데이터 고유 번호 값", example = "2")
		private Long addiction_test_id;
	}
}
