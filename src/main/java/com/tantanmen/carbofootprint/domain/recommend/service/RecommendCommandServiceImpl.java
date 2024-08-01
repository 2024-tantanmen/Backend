package com.tantanmen.carbofootprint.domain.recommend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.recommend.entity.FoodRecommend;
import com.tantanmen.carbofootprint.domain.recommend.entity.mapping.FoodRecommendResult;
import com.tantanmen.carbofootprint.domain.recommend.entity.mapping.MemberFoodRecommend;
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
			.recommendAllergen(request.getAllergen_list().toString())
			.recommendPreference(request.getPreference_list().toString())
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



}
