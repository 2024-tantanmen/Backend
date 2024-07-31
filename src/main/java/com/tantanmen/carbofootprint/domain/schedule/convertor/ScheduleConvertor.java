package com.tantanmen.carbofootprint.domain.schedule.convertor;

import java.util.ArrayList;
import java.util.List;

import com.tantanmen.carbofootprint.domain.schedule.entity.Schedule;
import com.tantanmen.carbofootprint.domain.schedule.entity.meal.FirstMeal;
import com.tantanmen.carbofootprint.domain.schedule.entity.meal.OtherMeal;
import com.tantanmen.carbofootprint.domain.schedule.entity.meal.SecondMeal;
import com.tantanmen.carbofootprint.domain.schedule.entity.meal.ThirdMeal;
import com.tantanmen.carbofootprint.domain.schedule.enums.FoodType;
import com.tantanmen.carbofootprint.domain.schedule.web.dto.ScheduleRequestDto;

public class ScheduleConvertor {
	/**
	 * 요청 DTO -> Schedule 객체
	 * 식단 정보는 따로 연관관계 매핑
	 */
	public static Schedule toSchedule(ScheduleRequestDto.AddScheduleRequestDto request){
		Schedule schedule = Schedule.builder()
			.totalKcal(request.getTotal_kcal())
			.exerciseDuration(request.getExercise_duration())
			.stepCount(request.getStep_count())
			.firstMealList(new ArrayList<>())
			.secondMealList(new ArrayList<>())
			.thirdMealList(new ArrayList<>())
			.otherMealList(new ArrayList<>())
			.build();

		addMealList(request, schedule);

		return schedule;
	}




	/**
	 * FoodType 요청 List -> 식단 데이터로 변경
	 */

	private static void addMealList(ScheduleRequestDto.AddScheduleRequestDto request, Schedule schedule){
		toFirstMealList(request.getFirst_meal(), schedule);
		toSecondMealList(request.getSecond_meal(), schedule);
		toThirdMealList(request.getThird_meal(), schedule);
		toOtherMealList(request.getOther_meal(), schedule);
	}

	private static void toFirstMealList(List<FoodType> request, Schedule schedule){
		request.stream().forEach(foodType -> {
			FirstMeal firstMeal = FirstMeal.builder()
				.foodType(foodType)
				.build();

			schedule.addFirstMeal(firstMeal);
		});
	}

	private static void toSecondMealList(List<FoodType> request, Schedule schedule){
		request.stream().forEach(foodType -> {
			SecondMeal secondMeal = SecondMeal.builder()
				.foodType(foodType)
				.build();

			schedule.addSecondMeal(secondMeal);
		});
	}

	private static void toThirdMealList(List<FoodType> request, Schedule schedule){
		request.stream().forEach(foodType -> {
			ThirdMeal thirdMeal = ThirdMeal.builder()
				.foodType(foodType)
				.build();

			schedule.addThirdMeal(thirdMeal);
		});
	}

	private static void toOtherMealList(List<FoodType> request, Schedule schedule){
		request.stream().forEach(foodType -> {
			OtherMeal otherMeal = OtherMeal.builder()
				.foodType(foodType)
				.build();

			schedule.addOtherMeal(otherMeal);
		});
	}
}
