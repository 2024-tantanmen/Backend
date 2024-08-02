package com.tantanmen.carbofootprint.domain.addiction_test.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(title = "ADDICTION_REQ_01", description = "탄수 중독 테스트 요청 DTO")
public class AddictionTestRequestDto {
	@Getter
	@Setter
	@ToString
	public static class SaveAddictionTestRequestDto{
		@Schema(description = "테스트에서 체크한 항목 총 개수", example = "6")
		@NotNull(message = "체크된 항목 개수를 입력해주세요.")
		@Min(value = 0, message = "최소 체크 개수는 0개 입니다.")
		@Max(value = 10, message = "최대 체크 개수는 10개 입니다.")
		private Integer check_count;

	}
}
