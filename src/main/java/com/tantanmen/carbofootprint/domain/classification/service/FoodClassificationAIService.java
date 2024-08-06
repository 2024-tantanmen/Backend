package com.tantanmen.carbofootprint.domain.classification.service;

import org.springframework.web.multipart.MultipartFile;

public interface FoodClassificationAIService {
	String detectFood(MultipartFile image);
}
