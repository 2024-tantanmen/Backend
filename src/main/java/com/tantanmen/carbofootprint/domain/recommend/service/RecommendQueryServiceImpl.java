package com.tantanmen.carbofootprint.domain.recommend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tantanmen.carbofootprint.domain.recommend.entity.FoodRecommend;
import com.tantanmen.carbofootprint.domain.recommend.entity.Preference;
import com.tantanmen.carbofootprint.domain.recommend.enums.PreferenceType;
import com.tantanmen.carbofootprint.domain.recommend.exception.AllergenEmptyException;
import com.tantanmen.carbofootprint.domain.recommend.exception.PreferenceEmptyException;
import com.tantanmen.carbofootprint.domain.recommend.repository.FoodRepository;
import com.tantanmen.carbofootprint.domain.recommend.repository.PreferenceRepository;
import com.tantanmen.carbofootprint.domain.recommend.web.dto.RecommendRequestDto;

import lombok.RequiredArgsConstructor;

/**
 *  선택된 알레르기 식습관에 따른
 *  음식 추천 기능
 */

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecommendQueryServiceImpl implements RecommendQueryService {

	private final FoodRepository foodRepository;
	private final PreferenceRepository preferenceRepository;

	@Override
	public List<FoodRecommend> recommendFoods(RecommendRequestDto.RecommendFoodRequestDto request){
		// 선택된 알레르기가 없는 경우
		if(request.getAllergen_list().isEmpty()){
			throw new AllergenEmptyException();
		}

		// 선택된 식습관이 없는 경우
		if(request.getPreference_list().isEmpty()){
			throw new PreferenceEmptyException();

		}


		List<String> allergenNameList = request.getAllergen_list().stream().map(Enum::name).collect(Collectors.toList());
		List<String> preferenceNameList;

		// 가리는 것 없음 선택 시 => 모두 추천 가능
		if(request.getPreference_list().contains(PreferenceType.없음)){
			preferenceNameList = preferenceRepository.findAll()
				.stream()
				.map(Preference::getPreferenceName)
				.collect(Collectors.toList());
		}
		else{
			preferenceNameList = request.getPreference_list().stream().map(Enum::name).collect(Collectors.toList());
		}

		return foodRepository.findRecommendedFoods(allergenNameList, preferenceNameList);
	}
}
