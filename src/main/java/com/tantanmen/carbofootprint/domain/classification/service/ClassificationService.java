package com.tantanmen.carbofootprint.domain.classification.service;

import com.tantanmen.carbofootprint.domain.classification.entity.Food;
import com.tantanmen.carbofootprint.domain.classification.web.dto.ClassificationRequestDto;

public interface ClassificationService {
	Food foodClassification(ClassificationRequestDto.FoodClassificationRequestDto request);
}
