package com.tantanmen.carbofootprint.domain.mypage.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tantanmen.carbofootprint.domain.classification.service.ClassificationService;
import com.tantanmen.carbofootprint.domain.community.service.ChatQueryServiceImpl;
import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.mypage.web.dto.MyPageResponseDto;
import com.tantanmen.carbofootprint.domain.recommend.service.RecommendQueryService;
import com.tantanmen.carbofootprint.global.annotation.LoginMember;
import com.tantanmen.carbofootprint.global.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "마이페이지 관련 Rest API", description = "마이페이지 사용자 데이터 목록 조회 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/my-page")
public class MyPageRestController {
	private final RecommendQueryService recommendQueryService;
	private final ChatQueryServiceImpl chatQueryService;
	private final ClassificationService classificationService;

	@Operation(summary = "음식 추천 목록 조회 API", description = "사용자 맞춤 음식 추천 기록 조회 API")
	@ApiResponses(value = {
		@io.swagger.v3.oas.annotations.responses.ApiResponse(
			responseCode = "COMMON200",
			description = "요청 성공",
			content = {
				@Content(
					schema = @Schema(
						implementation = MyPageResponseDto.MyPageRecommendResponseDto.class
					)
				)
			}
		)
	})
	@GetMapping("/recommend")
	public ApiResponse<List<MyPageResponseDto.MyPageRecommendResponseDto>> getRecommendList(@Parameter(hidden = true) @LoginMember Member member){
		return ApiResponse.onSuccess(recommendQueryService.getRecommendList(member));
	}


	@Operation(summary = "채팅 방 목록 조회 API", description = "사용자가 입장한 채팅 방 목록 조회 API")
	@ApiResponses(value = {
		@io.swagger.v3.oas.annotations.responses.ApiResponse(
			responseCode = "COMMON200",
			description = "요청 성공",
			content = {
				@Content(
					schema = @Schema(
						implementation = MyPageResponseDto.MyPageChatRoomResponseDto.class
					)
				)
			}
		)
	})
	@GetMapping("/chat")
	public ApiResponse<List<MyPageResponseDto.MyPageChatRoomResponseDto>> getChatRoomList(@Parameter(hidden = true) @LoginMember Member member){
		return ApiResponse.onSuccess(chatQueryService.getMyPageChatRoomList(member));
	}


	@Operation(summary = "음식 사진 인식 기록 목록 조회 API", description = "사용자가 요청한 음식 사진 인식 결과 목록 조회 API")
	@ApiResponses(value = {
		@io.swagger.v3.oas.annotations.responses.ApiResponse(
			responseCode = "COMMON200",
			description = "요청 성공",
			content = {
				@Content(
					schema = @Schema(
						implementation = MyPageResponseDto.MyPageClassificationResponseDto.class
					)
				)
			}
		)
	})
	@GetMapping("/classification")
	public ApiResponse<List<MyPageResponseDto.MyPageClassificationResponseDto>> getClassificationList(@Parameter(hidden = true) @LoginMember Member member){
		return ApiResponse.onSuccess(classificationService.getMyPageClassificationResultList(member));
	}
}
