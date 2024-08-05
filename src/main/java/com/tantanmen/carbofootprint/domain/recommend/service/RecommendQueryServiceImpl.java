package com.tantanmen.carbofootprint.domain.recommend.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.mypage.web.dto.MyPageResponseDto;
import com.tantanmen.carbofootprint.domain.recommend.converter.RecommendConverter;
import com.tantanmen.carbofootprint.domain.recommend.entity.Allergen;
import com.tantanmen.carbofootprint.domain.recommend.entity.FoodRecommend;
import com.tantanmen.carbofootprint.domain.recommend.entity.Preference;
import com.tantanmen.carbofootprint.domain.recommend.entity.mapping.FoodRecommendResult;
import com.tantanmen.carbofootprint.domain.recommend.entity.mapping.MemberFoodRecommend;
import com.tantanmen.carbofootprint.domain.recommend.enums.PreferenceType;
import com.tantanmen.carbofootprint.domain.recommend.exception.AllergenEmptyException;
import com.tantanmen.carbofootprint.domain.recommend.exception.PreferenceEmptyException;
import com.tantanmen.carbofootprint.domain.recommend.repository.FoodRecommendRepository;
import com.tantanmen.carbofootprint.domain.recommend.repository.MemberFoodRecommendRepository;
import com.tantanmen.carbofootprint.domain.recommend.repository.PreferenceRepository;
import com.tantanmen.carbofootprint.domain.recommend.web.dto.RecommendRequestDto;
import com.tantanmen.carbofootprint.domain.recommend.web.dto.RecommendResponseDto;
import com.tantanmen.carbofootprint.global.enums.statuscode.ErrorStatus;
import com.tantanmen.carbofootprint.global.exception.GeneralException;
import com.tantanmen.carbofootprint.global.util.LocalDateTimeFormatter;

import lombok.RequiredArgsConstructor;

/**
 *  선택된 알레르기 식습관에 따른
 *  음식 추천 기능
 */

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecommendQueryServiceImpl implements RecommendQueryService {

	private final FoodRecommendRepository foodRecommendRepository;
	private final PreferenceRepository preferenceRepository;
	private final MemberFoodRecommendRepository memberFoodRecommendRepository;

	/**
	 * 음식 추천
	 */
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

		return foodRecommendRepository.findRecommendedFoods(allergenNameList, preferenceNameList);
	}

	/**
	 * 마이페이지 음식 추천 내역
	 */
	@Override
	public List<MyPageResponseDto.MyPageRecommendResponseDto> getRecommendList(Member member){
		List<MyPageResponseDto.MyPageRecommendResponseDto> result = new ArrayList<>();

		for (MemberFoodRecommend memberFoodRecommend : memberFoodRecommendRepository.findByMemberId(member.getId())) {
			// 추천 음식 상세 정보 DTO
			List<RecommendResponseDto.RecommendFoodDetailDto> foodDetailDtos = new ArrayList<>();

			for (FoodRecommendResult foodRecommendResult : memberFoodRecommend.getFoodRecommendResultList()) {
				FoodRecommend foodRecommend = foodRecommendResult.getFoodRecommend();
				RecommendResponseDto.RecommendFoodDetailDto detailDto = RecommendResponseDto.RecommendFoodDetailDto.builder()
					.name(foodRecommend.getFoodName())
					.image_url(foodRecommend.getImageUrl())
					.calorie(foodRecommend.getCalorie())
					.carbohydrate(foodRecommend.getCarbohydrate())
					.saccharide(foodRecommend.getSaccharide())
					.category_list(RecommendConverter.getCategoryList(foodRecommend))
					.build();

				foodDetailDtos.add(detailDto);
			}

			// 사용자가 선택한 알레르기, 식습관 정보와 저장된 시각 데이터
			MyPageResponseDto.MyPageRecommendResponseDto resultDto = MyPageResponseDto.MyPageRecommendResponseDto.builder()
				.member_recommend_id(memberFoodRecommend.getId())
				.allergen_list(Arrays.stream(memberFoodRecommend.getRecommendAllergen().split("#")).toList())
				.preference_list(Arrays.stream(memberFoodRecommend.getRecommendPreference().split("#")).toList())
				.date(LocalDateTimeFormatter.formatLocalDateTime(memberFoodRecommend.getCreatedAt()))
				.recommend_food_list(foodDetailDtos)
				.build();

			result.add(resultDto);
		}
		return result;
	}

	/**
	 * 사용자 저장 데이터 하나 가져오기 (링크 공유용)
	 */
	@Override
	public RecommendResponseDto.RecommendFoodResponseDto getOneRecommendResult(Long memberRecommendId){
		MemberFoodRecommend memberFoodRecommend = memberFoodRecommendRepository.findById(memberRecommendId)
			.orElseThrow(() -> new GeneralException(ErrorStatus._MEMBER_RECOMMEND_RESULT_NOT_EXIST));

		List<RecommendResponseDto.RecommendFoodDetailDto> foodList = new ArrayList<>();
		for (FoodRecommendResult foodRecommendResult : memberFoodRecommend.getFoodRecommendResultList()) {
			FoodRecommend foodRecommend = foodRecommendResult.getFoodRecommend();
			RecommendResponseDto.RecommendFoodDetailDto resultDto = RecommendResponseDto.RecommendFoodDetailDto.builder()
				.name(foodRecommend.getFoodName())
				.image_url(foodRecommend.getImageUrl())
				.calorie(foodRecommend.getCalorie())
				.carbohydrate(foodRecommend.getCarbohydrate())
				.saccharide(foodRecommend.getSaccharide())
				.build();

			foodList.add(resultDto);
		}

		return RecommendResponseDto.RecommendFoodResponseDto.builder()
			.member_recommend_id(memberFoodRecommend.getId())
			.food_list(foodList)
			.allergen_list(Arrays.stream(memberFoodRecommend.getRecommendAllergen().split("#")).toList())
			.preference_list(Arrays.stream(memberFoodRecommend.getRecommendPreference().split("#")).toList())
			.build();
	}

}
