package com.tantanmen.carbofootprint.domain.classification.service;

import com.tantanmen.carbofootprint.domain.classification.entity.ClassificationResult;
import com.tantanmen.carbofootprint.domain.classification.entity.Food;
import com.tantanmen.carbofootprint.domain.classification.web.dto.ClassificationRequestDto;
import com.tantanmen.carbofootprint.domain.member.entity.Member;

public interface ClassificationService {
	Food foodClassification(ClassificationRequestDto.FoodClassificationRequestDto request);
	ClassificationResult saveClassificationResult(ClassificationRequestDto.SaveClassificationResultRequestDto request, Member member);
}
