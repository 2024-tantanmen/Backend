package com.tantanmen.carbofootprint.domain.schedule.web.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.tantanmen.carbofootprint.domain.schedule.enums.FoodType;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ScheduleRequestDto {
	@Setter
	@Getter
	@ToString
	/**
	 * 일정 생성 요청 DTO
	 */
	public static class AddScheduleRequestDto{
		@Length(max = 10, message = "일정 이름은 최대 10글자 입니다.")
		@NotBlank(message = "일정 이름을 작성해주세요.")
		private String name;
		@NotNull(message = "날짜를 입력해주세요.")
		@Min(value = 8, message = "8월 이상의 데이터만 요청 가능합니다.")
		@Max(value = 9, message = "최대 9월까지의 데이터만 요청 가능합니다.")
		private Long month;

		@NotNull(message = "날짜를 입력해주세요.")
		@Min(value = 1, message = "날짜 입력이 잘못되었습니다.")
		@Max(value = 31, message = "날짜 입력이 잘못되었습니다.")
		private Long day;
		private List<FoodType> first_meal;
		private List<FoodType> second_meal;
		private List<FoodType> third_meal;
		private List<FoodType> other_meal;
		private Long total_kcal;
		private Long exercise_duration;
		private Long step_count;
	}
}
