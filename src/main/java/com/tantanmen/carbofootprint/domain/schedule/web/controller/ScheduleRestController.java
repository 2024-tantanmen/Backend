package com.tantanmen.carbofootprint.domain.schedule.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.schedule.convertor.ScheduleConvertor;
import com.tantanmen.carbofootprint.domain.schedule.entity.Schedule;
import com.tantanmen.carbofootprint.domain.schedule.service.ScheduleCommandService;
import com.tantanmen.carbofootprint.domain.schedule.service.ScheduleQueryService;
import com.tantanmen.carbofootprint.domain.schedule.web.dto.ScheduleRequestDto;
import com.tantanmen.carbofootprint.domain.schedule.web.dto.ScheduleResponseDto;
import com.tantanmen.carbofootprint.global.annotation.LoginMember;
import com.tantanmen.carbofootprint.global.response.ApiResponse;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
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
	private final ScheduleQueryService scheduleQueryService;

	/**
	 * 일정 생성 요청 API
	 */
	@PostMapping("")
	public ApiResponse<ScheduleResponseDto.AddScheduleResponseDto> addSchedule(@Valid @RequestBody ScheduleRequestDto.AddScheduleRequestDto request, @Parameter(hidden = true) @LoginMember Member member){
		Schedule schedule = scheduleCommandService.addSchedule(request, member);
		ScheduleResponseDto.AddScheduleResponseDto result = ScheduleResponseDto.AddScheduleResponseDto.builder()
			.schedule_id(schedule.getId())
			.build();
		return ApiResponse.onSuccess(result);
	}

	/**
	 * 사용자 일정 전체 조회 API
	 */
	@GetMapping("")
	public ApiResponse<ScheduleResponseDto.FindAllScheduleResponseDto> getAllSchedules(@Parameter(hidden = true) @LoginMember Member member){
		List<Schedule> scheduleList = scheduleQueryService.getAllSchedules(member);
		return ApiResponse.onSuccess(ScheduleConvertor.toFindAllScheduleResponseDto(scheduleList));
	}

	/**
	 * 일정 변경 요청 API
	 */
	@PutMapping("/{scheduleId}")
	public ApiResponse<ScheduleResponseDto.UpdateScheduleResponseDto> updateSchedule(@Valid @RequestBody
	ScheduleRequestDto.AddScheduleRequestDto request, @PathVariable(name = "scheduleId") Long scheduleId, @Parameter(hidden = true) @LoginMember Member member){
		Schedule schedule = scheduleCommandService.updateSchedule(request, scheduleId, member);
		ScheduleResponseDto.UpdateScheduleResponseDto result = ScheduleResponseDto.UpdateScheduleResponseDto.builder()
			.schedule_id(schedule.getId())
			.build();

		return ApiResponse.onSuccess(result);
	}
}
