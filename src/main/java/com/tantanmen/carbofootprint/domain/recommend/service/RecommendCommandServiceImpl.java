package com.tantanmen.carbofootprint.domain.recommend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.recommend.entity.Allergen;
import com.tantanmen.carbofootprint.domain.recommend.entity.FoodRecommend;
import com.tantanmen.carbofootprint.domain.recommend.entity.Preference;
import com.tantanmen.carbofootprint.domain.recommend.entity.mapping.FoodRecommendResult;
import com.tantanmen.carbofootprint.domain.recommend.entity.mapping.MemberFoodRecommend;
import com.tantanmen.carbofootprint.domain.recommend.enums.AllergenType;
import com.tantanmen.carbofootprint.domain.recommend.enums.PreferenceType;
import com.tantanmen.carbofootprint.domain.recommend.web.dto.RecommendRequestDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RecommendCommandServiceImpl implements RecommendCommandService{

	@Override
	public void saveResultData(Member member, List<FoodRecommend> foodRecommendList, RecommendRequestDto.RecommendFoodRequestDto request){
		MemberFoodRecommend memberFoodRecommend = MemberFoodRecommend.builder()
			.recommendAllergen(allergenListToString(request.getAllergen_list()))
			.recommendPreference(preferenceListToString(request.getPreference_list()))
			.foodRecommendResultList(new ArrayList<>())
			.build();

		for (FoodRecommend foodRecommend : foodRecommendList) {
			FoodRecommendResult foodRecommendResult = FoodRecommendResult.builder()
				.build();

			foodRecommend.addFoodRecommendResult(foodRecommendResult);
			memberFoodRecommend.addFoodRecommendResult(foodRecommendResult);
		}

		member.addMemberFoodRecommend(memberFoodRecommend);
	}

	private String allergenListToString(List<AllergenType> allergenList){
		String result = "";
		int len = allergenList.size();
		// 무조건 한 개 이상
		if(len >= 1) {
			result += allergenList.get(0).name();
		}
		for(int i = 1 ; i < len ; i ++){
			result += "#" + allergenList.get(i).name();
		}

		return result;
	}

	private String preferenceListToString(List<PreferenceType> preferenceList){
		String result = "";
		int len = preferenceList.size();
		// 무조건 한 개 이상
		if(len >= 1) {
			result += preferenceList.get(0).name();
		}
		for(int i = 1 ; i < len ; i ++){
			result += "#" + preferenceList.get(i).name();
		}

		return result;
	}


}
