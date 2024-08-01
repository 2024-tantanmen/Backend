package com.tantanmen.carbofootprint.domain.recommend.service;

import java.util.List;

import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.recommend.entity.FoodRecommend;
import com.tantanmen.carbofootprint.domain.recommend.web.dto.RecommendRequestDto;

public interface RecommendCommandService {
	void saveResultData(Member member, List<FoodRecommend> foodRecommendList, RecommendRequestDto.RecommendFoodRequestDto request);
}
