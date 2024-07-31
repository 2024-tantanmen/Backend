package com.tantanmen.carbofootprint.domain.schedule.web.dto;

import java.util.List;

import com.tantanmen.carbofootprint.domain.schedule.enums.FoodType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ScheduleResponseDto {

	/**
	 * 일정 생성 응답 DTO
	 */
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class AddScheduleResponseDto{
		private Long schedule_id;
	}

	/**
	 * 모든 일정 응답 DTO
	 */
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class FindAllScheduleResponseDto{
		private List<SchedulePreviewResponseDto> august_schedule_list;
		private List<SchedulePreviewResponseDto> september_schedule_list;
	}

	/**
	 * 모든 일정 응답 상세 DTO
	 */
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class SchedulePreviewResponseDto{
		private String title;
		private List<FoodType> firstMeal;
		private List<FoodType> secondMeal;
		private List<FoodType> thirdMeal;
		private List<FoodType> extraMeal;
		private Long calorie;
		private Long workoutTime;
		private Long stepCount;
		private Long day;
	}

	/**
	 * 일정 수정 응답 DTO
	 */
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class UpdateScheduleResponseDto{
		private Long update_schedule_id;
	}

	/**
	 * 일정 삭제 응답 DTO
	 */
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class DeleteScheduleResponseDto{
		private Long deleted_schedule_id;
	}
}
