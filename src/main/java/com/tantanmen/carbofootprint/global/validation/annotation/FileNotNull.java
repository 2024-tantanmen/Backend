package com.tantanmen.carbofootprint.global.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.tantanmen.carbofootprint.global.validation.validator.FileNotNullConstraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Multipartfile 객체에 대한 Null 값 검증
 */
@Target(value = {ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileNotNullConstraint.class)
public @interface FileNotNull {
	String message() default "파일이 존재하지 않습니다.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
