package com.tantanmen.carbofootprint.domain.addiction_test.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tantanmen.carbofootprint.domain.addiction_test.entity.AddictionTest;
import com.tantanmen.carbofootprint.domain.addiction_test.service.AddictionTestCommandService;
import com.tantanmen.carbofootprint.domain.addiction_test.web.dto.AddictionTestRequestDto;
import com.tantanmen.carbofootprint.domain.addiction_test.web.dto.AddictionTestResponseDto;
import com.tantanmen.carbofootprint.domain.member.entity.Member;
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
 * 탄수화물 중독 테스트 결과 데이터 저장 controller
 */
@Tag(name = "탄수화물 중독 테스트 관련 API", description = "탄수화물 중독 테스트 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/addiction-tests")
public class AddictionTestRestController {
	private final AddictionTestCommandService addictionTestCommandService;
	@Operation(summary = "탄수 중독 테스트 저장 API", description = "탄수 중독 테스트 결과 데이터 저장 API")
	@ApiResponses(value = {
		@io.swagger.v3.oas.annotations.responses.ApiResponse(
			responseCode = "COMMON200",
			description = "요청 성공",
			content = {
				@Content(
					schema = @Schema(
						implementation = AddictionTestResponseDto.SaveAddictionTestResponseDto.class
					)
				)
			}
		)
	})
	@PostMapping()
	public ApiResponse<AddictionTestResponseDto.SaveAddictionTestResponseDto> saveResult(@Valid @RequestBody AddictionTestRequestDto.SaveAddictionTestRequestDto request, @Parameter(hidden = true) @LoginMember
		Member member){
		AddictionTest addictionTest = addictionTestCommandService.saveResult(request, member);
		AddictionTestResponseDto.SaveAddictionTestResponseDto result = AddictionTestResponseDto.SaveAddictionTestResponseDto.builder()
			.addiction_test_id(addictionTest.getId())
			.build();

		return ApiResponse.onSuccess(result);
	}
}
