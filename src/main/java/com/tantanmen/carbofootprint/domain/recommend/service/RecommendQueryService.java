package com.tantanmen.carbofootprint.domain.recommend.service;

import java.util.List;

import com.tantanmen.carbofootprint.domain.recommend.entity.FoodRecommend;
import com.tantanmen.carbofootprint.domain.recommend.web.dto.RecommendRequestDto;

public interface RecommendQueryService {
	List<FoodRecommend> recommendFoods(RecommendRequestDto.RecommendFoodRequestDto request);

}
