package com.tantanmen.carbofootprint.domain.schedule.web.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.tantanmen.carbofootprint.domain.schedule.enums.FoodType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(title = "SCHEDULE_REQ_01", description = "일정 요청 DTO")
public class ScheduleRequestDto {
	@Setter
	@Getter
	@ToString
	/**
	 * 일정 생성 요청 DTO
	 */
	public static class AddScheduleRequestDto{
		@Schema(description = "일정 이름", example = "무탄수운동 1일차")
		@Length(max = 10, message = "일정 이름은 최대 10글자 입니다.")
		@NotBlank(message = "일정 이름을 작성해주세요.")
		private String title;
		@Schema(description = "일정 월", example = "8")
		@NotNull(message = "날짜를 입력해주세요.")
		@Min(value = 8, message = "8월 이상의 데이터만 요청 가능합니다.")
		@Max(value = 9, message = "최대 9월까지의 데이터만 요청 가능합니다.")
		private Long month;

		@Schema(description = "일정 일", example = "30")
		@NotNull(message = "날짜를 입력해주세요.")
		@Min(value = 1, message = "날짜 입력이 잘못되었습니다.")
		@Max(value = 31, message = "날짜 입력이 잘못되었습니다.")
		private Long day;
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
	}
}
