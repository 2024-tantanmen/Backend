package com.tantanmen.carbofootprint.domain.classification.service;

import java.util.List;

import com.tantanmen.carbofootprint.domain.classification.entity.ClassificationResult;
import com.tantanmen.carbofootprint.domain.classification.entity.Food;
import com.tantanmen.carbofootprint.domain.classification.web.dto.ClassificationRequestDto;
import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.mypage.web.dto.MyPageResponseDto;

public interface ClassificationService {
	Food foodClassification(ClassificationRequestDto.FoodClassificationRequestDto request);
	ClassificationResult saveClassificationResult(ClassificationRequestDto.SaveClassificationResultRequestDto request, Member member);
	List<MyPageResponseDto.MyPageClassificationResponseDto> getMyPageClassificationResultList(Member member);
}
