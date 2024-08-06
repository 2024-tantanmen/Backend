package com.tantanmen.carbofootprint.domain.recommend.exception;

import com.tantanmen.carbofootprint.global.enums.statuscode.ErrorStatus;
import com.tantanmen.carbofootprint.global.exception.GeneralException;

/**
 * 알레르기를 선택하지 않은 경우 예외 처리
 */
public class AllergenEmptyException extends GeneralException {
	public AllergenEmptyException() {
		super(ErrorStatus._ALLERGEN_EMPTY_ERROR);
	}
}
