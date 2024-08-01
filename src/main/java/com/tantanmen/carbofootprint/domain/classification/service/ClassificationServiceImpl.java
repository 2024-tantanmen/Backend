package com.tantanmen.carbofootprint.domain.classification.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tantanmen.carbofootprint.domain.classification.entity.ClassificationResult;
import com.tantanmen.carbofootprint.domain.classification.entity.Food;
import com.tantanmen.carbofootprint.domain.classification.repository.ClassificationRepository;
import com.tantanmen.carbofootprint.domain.classification.repository.FoodRepository;
import com.tantanmen.carbofootprint.domain.classification.web.dto.ClassificationRequestDto;
import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.global.aws.s3.AmazonS3Manager;
import com.tantanmen.carbofootprint.global.enums.statuscode.ErrorStatus;
import com.tantanmen.carbofootprint.global.exception.GeneralException;

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
	private final ClassificationRepository classificationRepository;

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

		classificationRepository.save(result);
		return result;
	}

}
