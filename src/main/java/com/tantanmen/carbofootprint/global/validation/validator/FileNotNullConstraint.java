package com.tantanmen.carbofootprint.global.validation.validator;

import org.springframework.web.multipart.MultipartFile;

import com.tantanmen.carbofootprint.global.validation.annotation.FileNotNull;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileNotNullConstraint implements ConstraintValidator<FileNotNull, MultipartFile> {

	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		boolean isValid = (!value.isEmpty());

		if(!isValid){
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("파일이 존재하지 않습니다.").addConstraintViolation();
		}
		return isValid;
	}

	@Override
	public void initialize(FileNotNull constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
}
