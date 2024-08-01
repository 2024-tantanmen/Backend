package com.tantanmen.carbofootprint.domain.schedule.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.recommend.web.dto.RecommendResponseDto;
import com.tantanmen.carbofootprint.domain.schedule.convertor.ScheduleConvertor;
import com.tantanmen.carbofootprint.domain.schedule.entity.Schedule;
import com.tantanmen.carbofootprint.domain.schedule.service.ScheduleCommandService;
import com.tantanmen.carbofootprint.domain.schedule.service.ScheduleQueryService;
import com.tantanmen.carbofootprint.domain.schedule.web.dto.ScheduleRequestDto;
import com.tantanmen.carbofootprint.domain.schedule.web.dto.ScheduleResponseDto;
import com.tantanmen.carbofootprint.global.annotation.LoginMember;
import com.tantanmen.carbofootprint.global.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 탄수 발자국 일정 관련 Rest API
 */
@Tag(name = "탄수 발자국 일정 API", description = "탄수 발자국 일정 API")
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
	@Operation(summary = "일정 생성 요청 API", description = "일정 생성 요청 API")
	@ApiResponses(value = {
		@io.swagger.v3.oas.annotations.responses.ApiResponse(
			responseCode = "COMMON200",
			description = "요청 성공",
			content = {
				@Content(
					schema = @Schema(
						implementation = ScheduleResponseDto.AddScheduleResponseDto.class
					)
				)
			}
		)
	})
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
	@Operation(summary = "일정 조회 요청 API", description = "사용자 일정 전체 조회 API")
	@ApiResponses(value = {
		@io.swagger.v3.oas.annotations.responses.ApiResponse(
			responseCode = "COMMON200",
			description = "요청 성공",
			content = {
				@Content(
					schema = @Schema(
						implementation = ScheduleResponseDto.FindAllScheduleResponseDto.class
					)
				)
			}
		)
	})
	@GetMapping("")
	public ApiResponse<ScheduleResponseDto.FindAllScheduleResponseDto> getAllSchedules(@Parameter(hidden = true) @LoginMember Member member){
		List<Schedule> scheduleList = scheduleQueryService.getAllSchedules(member);
		return ApiResponse.onSuccess(ScheduleConvertor.toFindAllScheduleResponseDto(scheduleList));
	}

	/**
	 * 일정 변경 요청 API
	 */
	@Operation(summary = "일정 변경 요청 API", description = "일정 변경 요청 API")
	@ApiResponses(value = {
		@io.swagger.v3.oas.annotations.responses.ApiResponse(
			responseCode = "COMMON200",
			description = "요청 성공",
			content = {
				@Content(
					schema = @Schema(
						implementation = ScheduleResponseDto.UpdateScheduleResponseDto.class
					)
				)
			}
		),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(
			responseCode = "SCHEDULE4001",
			description = "일정 데이터 고유 번호와 일치하는 일정 데이터가 없는 경우",
			content = {
				@Content(
					schema = @Schema(
						example = "존재하지 않는 일정입니다."
					)
				)
			}
		),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(
			responseCode = "SCHEDULE4002",
			description = "사용자가 작성하지 않은 일정 데이터를 변경하려는 경우",
			content = {
				@Content(
					schema = @Schema(
						example = "일정에 접근 권한이 없는 사용자입니다."
					)
				)
			}
		)
	})
	@PutMapping("/{scheduleId}")
	public ApiResponse<ScheduleResponseDto.UpdateScheduleResponseDto> updateSchedule(@Valid @RequestBody
	ScheduleRequestDto.AddScheduleRequestDto request, @PathVariable(name = "scheduleId") Long scheduleId, @Parameter(hidden = true) @LoginMember Member member){
		Schedule schedule = scheduleCommandService.updateSchedule(request, scheduleId, member);
		ScheduleResponseDto.UpdateScheduleResponseDto result = ScheduleResponseDto.UpdateScheduleResponseDto.builder()
			.update_schedule_id(schedule.getId())
			.build();

		return ApiResponse.onSuccess(result);
	}

	/**
	 * 일정 삭제 요청 API
	 */
	@Operation(summary = "일정 삭제 요청 API", description = "일정 삭제 요청 API")
	@ApiResponses(value = {
		@io.swagger.v3.oas.annotations.responses.ApiResponse(
			responseCode = "COMMON200",
			description = "요청 성공",
			content = {
				@Content(
					schema = @Schema(
						implementation = ScheduleResponseDto.DeleteScheduleResponseDto.class
					)
				)
			}
		),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(
			responseCode = "SCHEDULE4001",
			description = "일정 데이터 고유 번호와 일치하는 일정 데이터가 없는 경우",
			content = {
				@Content(
					schema = @Schema(
						example = "존재하지 않는 일정입니다."
					)
				)
			}
		),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(
			responseCode = "SCHEDULE4002",
			description = "사용자가 작성하지 않은 일정 데이터를 변경하려는 경우",
			content = {
				@Content(
					schema = @Schema(
						example = "일정에 접근 권한이 없는 사용자입니다."
					)
				)
			}
		)
	})
	@DeleteMapping("/{scheduleId}")
	public ApiResponse<ScheduleResponseDto.DeleteScheduleResponseDto> deleteSchedule(@PathVariable(name = "scheduleId") Long scheduleId, @Parameter(hidden = true) @LoginMember Member member){
		Long deletedScheduleId = scheduleCommandService.deleteSchedule(scheduleId, member);
		ScheduleResponseDto.DeleteScheduleResponseDto result = ScheduleResponseDto.DeleteScheduleResponseDto
			.builder()
			.deleted_schedule_id(deletedScheduleId)
			.build();
		return ApiResponse.onSuccess(result);
	}
}
