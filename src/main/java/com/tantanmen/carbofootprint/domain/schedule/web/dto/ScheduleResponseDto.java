package com.tantanmen.carbofootprint.domain.schedule.web.dto;

import java.util.List;

import com.tantanmen.carbofootprint.domain.schedule.enums.FoodType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Schema(title = "SCHEDULE_RES_01", description = "일정 응답 DTO")
public class ScheduleResponseDto {

	/**
	 * 일정 생성 응답 DTO
	 */
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class AddScheduleResponseDto{
		@Schema(description = "생성된 일정 고유 번호", example = "10")
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
		@Schema(description = "전체 일정 월별 목록")
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
		@Schema(description = "일정 이름", example = "무탄수운동 1일차")
		private String title;
		@Schema(description = "첫 끼 목록", example = "[\"중식\", \"일식\"]")
		private List<FoodType> firstMeal;
		@Schema(description = "두 끼 목록", example = "[\"양식\", \"샐러드\"]")
		private List<FoodType> secondMeal;
		@Schema(description = "세 끼 목록", example = "[\"빵\", \"과일\", \"기타\"]")
		private List<FoodType> thirdMeal;
		@Schema(description = "더먹 끼 목록", example = "[]")
		private List<FoodType> extraMeal;
		@Schema(description = "총 섭취 칼로리", example = "2300")
		private Long calorie;
		@Schema(description = "운동 시간", example = "50")
		private Long workoutTime;
		@Schema(description = "걸음수", example = "23000")
		private Long stepCount;
		@Schema(description = "날짜", example = "30")
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
		@Schema(description = "변경한 일정 고유 번호 값 (요청한 값과 동일)", example = "15")
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
		@Schema(description = "삭제된 일정 고유 번호 값 (요청한 값과 동일)", example = "13")
		private Long deleted_schedule_id;
	}
}
