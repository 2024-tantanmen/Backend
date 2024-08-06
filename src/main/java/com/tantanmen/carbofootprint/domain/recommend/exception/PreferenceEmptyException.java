package com.tantanmen.carbofootprint.domain.recommend.exception;

import com.tantanmen.carbofootprint.global.enums.statuscode.ErrorStatus;
import com.tantanmen.carbofootprint.global.exception.GeneralException;

/**
 * 식습관을 선택하지 않은 경우 예외 처리
 */
public class PreferenceEmptyException extends GeneralException {
	public PreferenceEmptyException() {
		super(ErrorStatus._PREFERENCE_EMPTY_ERROR);
	}
}
