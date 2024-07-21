package com.tantanmen.carbofootprint.domain.recommend.service;

import java.util.List;

import com.tantanmen.carbofootprint.domain.recommend.entity.Food;
import com.tantanmen.carbofootprint.domain.recommend.web.dto.RecommendRequestDto;

public interface RecommendQueryService {
	List<Food> recommendFoods(RecommendRequestDto.RecommendFoodRequestDto request);

}
