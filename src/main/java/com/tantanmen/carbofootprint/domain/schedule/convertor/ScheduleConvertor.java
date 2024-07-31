package com.tantanmen.carbofootprint.domain.schedule.convertor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.tantanmen.carbofootprint.domain.schedule.entity.Schedule;
import com.tantanmen.carbofootprint.domain.schedule.entity.meal.FirstMeal;
import com.tantanmen.carbofootprint.domain.schedule.entity.meal.OtherMeal;
import com.tantanmen.carbofootprint.domain.schedule.entity.meal.SecondMeal;
import com.tantanmen.carbofootprint.domain.schedule.entity.meal.ThirdMeal;
import com.tantanmen.carbofootprint.domain.schedule.enums.FoodType;
import com.tantanmen.carbofootprint.domain.schedule.web.dto.ScheduleRequestDto;
import com.tantanmen.carbofootprint.domain.schedule.web.dto.ScheduleResponseDto;

public class ScheduleConvertor {
	/**
	 * 요청 DTO -> Schedule 객체
	 * 식단 정보는 따로 연관관계 매핑
	 */
	public static Schedule toSchedule(ScheduleRequestDto.AddScheduleRequestDto request){
		Schedule schedule = Schedule.builder()
			.totalKcal(request.getCalorie())
			.exerciseDuration(request.getWorkoutTime())
			.stepCount(request.getStepCount())
			.firstMealList(new ArrayList<>())
			.secondMealList(new ArrayList<>())
			.thirdMealList(new ArrayList<>())
			.otherMealList(new ArrayList<>())
			.month(request.getMonth())
			.day(request.getDay())
			.build();

		addMealList(request, schedule);

		return schedule;
	}




	/**
	 * FoodType 요청 List -> 식단 데이터로 변경
	 */

	public static void addMealList(ScheduleRequestDto.AddScheduleRequestDto request, Schedule schedule){
		toFirstMealList(request.getFirstMeal(), schedule);
		toSecondMealList(request.getSecondMeal(), schedule);
		toThirdMealList(request.getThirdMeal(), schedule);
		toOtherMealList(request.getExtraMeal(), schedule);
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

	/**
	 * List<Schedule> => List<DTO>로 변환
	 */
	public static ScheduleResponseDto.FindAllScheduleResponseDto toFindAllScheduleResponseDto(List<Schedule> scheduleList){
		List<ScheduleResponseDto.SchedulePreviewResponseDto> august_schedule_list = new ArrayList<>();
		List<ScheduleResponseDto.SchedulePreviewResponseDto> september_schedule_list = new ArrayList<>();

		for (Schedule schedule : scheduleList) {
			ScheduleResponseDto.SchedulePreviewResponseDto result = toSchedulePreviewResponseDto(
				schedule);
			if (schedule.getMonth() == 8){
				august_schedule_list.add(result);
			}
			else september_schedule_list.add(result);
		}

		return ScheduleResponseDto.FindAllScheduleResponseDto.builder()
			.august_schedule_list(august_schedule_list)
			.september_schedule_list(september_schedule_list)
			.build();
	}

	/**
	 * Schedule => DTO로 변환
	 */
	private static ScheduleResponseDto.SchedulePreviewResponseDto toSchedulePreviewResponseDto(Schedule schedule){
		return ScheduleResponseDto.SchedulePreviewResponseDto.builder()
			.title(schedule.getTitle())
			.calorie(schedule.getTotalKcal())
			.workoutTime(schedule.getExerciseDuration())
			.stepCount(schedule.getStepCount())
			.firstMeal(schedule.getFirstMealList().stream().map(FirstMeal::getFoodType).collect(Collectors.toList()))
			.secondMeal(schedule.getSecondMealList().stream().map(SecondMeal::getFoodType).collect(Collectors.toList()))
			.thirdMeal(schedule.getThirdMealList().stream().map(ThirdMeal::getFoodType).collect(Collectors.toList()))
			.extraMeal(schedule.getOtherMealList().stream().map(OtherMeal::getFoodType).collect(Collectors.toList()))
			.day(schedule.getDay())
			.build();
	}
}
