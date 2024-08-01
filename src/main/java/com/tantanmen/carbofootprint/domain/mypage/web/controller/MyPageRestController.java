package com.tantanmen.carbofootprint.domain.mypage.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tantanmen.carbofootprint.domain.community.service.ChatQueryServiceImpl;
import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.mypage.web.dto.MyPageResponseDto;
import com.tantanmen.carbofootprint.domain.recommend.service.RecommendQueryService;
import com.tantanmen.carbofootprint.global.annotation.LoginMember;
import com.tantanmen.carbofootprint.global.response.ApiResponse;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/my-page")
public class MyPageRestController {
	private final RecommendQueryService recommendQueryService;
	private final ChatQueryServiceImpl chatQueryService;

	@GetMapping("/recommend")
	public ApiResponse<List<MyPageResponseDto.MyPageRecommendResponseDto>> getRecommendList(@Parameter(hidden = true) @LoginMember Member member){
		return ApiResponse.onSuccess(recommendQueryService.getRecommendList(member));
	}

	@GetMapping("/chat")
	public ApiResponse<List<MyPageResponseDto.MyPageChatRoomResponseDto>> getChatRoomList(@Parameter(hidden = true) @LoginMember Member member){
		return ApiResponse.onSuccess(chatQueryService.getMyPageChatRoomList(member));
	}
}
