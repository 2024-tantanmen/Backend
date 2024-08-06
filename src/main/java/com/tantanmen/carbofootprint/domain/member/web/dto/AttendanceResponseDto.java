package com.tantanmen.carbofootprint.domain.member.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(title = "ATTENDANCE_RES_01", description = "연속 출석 요청 DTO")
public class AttendanceResponseDto {
	/**
	 * 연속 출석 일수 응답 DTO
	 */
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class GetAttendanceResponseDto{
		@Schema(description = "연속 출석 일수", example = "3")
		private Integer attendance_streak_count;
	}
}
