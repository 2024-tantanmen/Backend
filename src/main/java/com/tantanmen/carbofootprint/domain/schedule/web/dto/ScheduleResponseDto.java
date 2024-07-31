package com.tantanmen.carbofootprint.domain.schedule.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ScheduleResponseDto {
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	/**
	 * 일정 생성 응답 DTO
	 */
	public static class AddScheduleResponseDto{
		private Long schedule_id;
	}
}
