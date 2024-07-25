package com.tantanmen.carbofootprint.domain.classification.web.dto;

import org.springframework.web.multipart.MultipartFile;

import com.tantanmen.carbofootprint.global.validation.annotation.FileNotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ClassificationRequestDto {
	/**
	 * 음식 사진 인식 요청 Dto
	 */
	@Getter
	@Setter
	@ToString
	public static class FoodClassificationRequestDto {
		@FileNotNull
		@Schema(description = "음식 사진", example = "[음식 이미지]", type = "string", format = "binary")
		private MultipartFile image;
	}
}
