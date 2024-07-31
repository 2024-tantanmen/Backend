package com.tantanmen.carbofootprint.domain.schedule.web.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.tantanmen.carbofootprint.domain.schedule.enums.FoodType;

import jakarta.validation.constraints.NotBlank;
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
		private Long month;
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
