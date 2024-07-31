package com.tantanmen.carbofootprint.domain.schedule.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.schedule.entity.Schedule;
import com.tantanmen.carbofootprint.domain.schedule.service.ScheduleCommandService;
import com.tantanmen.carbofootprint.domain.schedule.web.dto.ScheduleRequestDto;
import com.tantanmen.carbofootprint.domain.schedule.web.dto.ScheduleResponseDto;
import com.tantanmen.carbofootprint.global.annotation.LoginMember;
import com.tantanmen.carbofootprint.global.response.ApiResponse;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 탄수 발자국 일정 관련 Rest API
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleRestController {
	private final ScheduleCommandService scheduleCommandService;

	@PostMapping("")
	public ApiResponse<ScheduleResponseDto.AddScheduleResponseDto> addSchedule(@Valid @RequestBody ScheduleRequestDto.AddScheduleRequestDto request, @Parameter(hidden = true) @LoginMember Member member){
		log.info("request = {}", request);
		Schedule schedule = scheduleCommandService.addSchedule(request, member);
		ScheduleResponseDto.AddScheduleResponseDto result = ScheduleResponseDto.AddScheduleResponseDto.builder()
			.schedule_id(schedule.getId())
			.build();
		return ApiResponse.onSuccess(result);
	}

	// @GetMapping("/{month}/{day}")
	// public void getSchedulesByDate(@Valid @Min(value = 8, message = "8월 이상의 데이터만 요청 가능합니다.") @Max(value = 9, message = "최대 9월까지의 데이터만 요청 가능합니다.") @RequestParam(name = "month") Long month, @Min(value = 1, message = "올바른 날짜 값을 입력해주세요.") @Max(value = 31, message = "올바른 날짜 값을 입력해주세요.") @RequestParam(name = "day") Long day, @LoginMember
	// 	Member member){
	//
	// }

	// @GetMapping("")
	// public void getAllSchedules(){
	//
	// }
}
