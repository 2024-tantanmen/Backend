package com.tantanmen.carbofootprint.domain.member.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class AttendanceResponseDto {
	/**
	 * 연속 출석 일수 응답 DTO
	 */
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class GetAttendanceResponseDto{
		private Integer attendance_streak_count;
	}
}
