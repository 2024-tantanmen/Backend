package com.tantanmen.carbofootprint.domain.classification.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tantanmen.carbofootprint.domain.classification.entity.ClassificationResult;
import com.tantanmen.carbofootprint.domain.classification.entity.Food;
import com.tantanmen.carbofootprint.domain.classification.repository.ClassificationResultRepository;
import com.tantanmen.carbofootprint.domain.classification.repository.FoodRepository;
import com.tantanmen.carbofootprint.domain.classification.web.dto.ClassificationRequestDto;
import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.mypage.web.dto.MyPageResponseDto;
import com.tantanmen.carbofootprint.global.aws.s3.AmazonS3Manager;
import com.tantanmen.carbofootprint.global.enums.statuscode.ErrorStatus;
import com.tantanmen.carbofootprint.global.exception.GeneralException;
import com.tantanmen.carbofootprint.global.util.LocalDateTimeFormatter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ClassificationServiceImpl implements ClassificationService{
	private final FoodClassificationAIService foodClassificationAIService;
	private final FoodRepository foodRepository;
	private final AmazonS3Manager amazonS3Manager;
	private final ClassificationResultRepository classificationResultRepository;

	/**
	 * 음식 사진 인식
	 */
	@Override
	public Food foodClassification(ClassificationRequestDto.FoodClassificationRequestDto request){
		// 음식 AI 호출 후 음식 코드 반환
		String code = foodClassificationAIService.detectFood(request.getImage());

		// 반환된 음식 코드를 이용해 데이터 불러오기
		Optional<Food> foodOptional = foodRepository.findByCode(code);
		if(foodOptional.isEmpty()){
			log.error("코드에 맞는 데이터가 존재하지 않습니다. code = {}", code);
			throw new GeneralException(ErrorStatus._INTERNAL_SERVER_ERROR);
		}

		return foodOptional.get();
	}

	/**
	 * 음식 사진 인식 결과 저장
	 */
	@Transactional
	@Override
	public ClassificationResult saveClassificationResult(ClassificationRequestDto.SaveClassificationResultRequestDto request, Member member){
		Optional<Food> foodOptional = foodRepository.findByCode(request.getFood_code());
		if(foodOptional.isEmpty()){
			log.error("코드에 맞는 데이터가 존재하지 않습니다. code = {}", request.getFood_code());
			throw new GeneralException(ErrorStatus._INTERNAL_SERVER_ERROR);
		}

		String uploadUrl = amazonS3Manager.uploadFile(
			"/classification/images" + member.getLoginId() + UUID.randomUUID().toString(), request.getImage());

		ClassificationResult result = ClassificationResult.builder()
			.imageUrl(uploadUrl)
			.build();

		member.addClassificationResult(result);
		foodOptional.get().addClassificationResult(result);

		classificationResultRepository.save(result);
		return result;
	}

	@Override
	public List<MyPageResponseDto.MyPageClassificationResponseDto> getMyPageClassificationResultList(Member member){
		List<MyPageResponseDto.MyPageClassificationResponseDto> result = new ArrayList<>();
		for (ClassificationResult classificationResult : classificationResultRepository.findByMemberId(
			member.getId())) {
			Food food = classificationResult.getFood();

			MyPageResponseDto.MyPageClassificationResponseDto dto = MyPageResponseDto.MyPageClassificationResponseDto.builder()
				.date(LocalDateTimeFormatter.formatLocalDateTime(classificationResult.getCreatedAt()))
				.image_url(classificationResult.getImageUrl())
				.food_code(food.getCode())
				.name(food.getName())
				.amount(food.getAmount())
				.calorie(food.getKcal())
				.carb(food.getCarbohydrate())
				.prot(food.getProtein())
				.fat(food.getFat())
				.sugar(food.getSugar())
				.build();
			result.add(dto);
		}
		return result;
	}

}
