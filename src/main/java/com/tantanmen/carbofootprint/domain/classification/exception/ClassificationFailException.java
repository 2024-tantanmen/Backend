package com.tantanmen.carbofootprint.domain.classification.exception;

import com.tantanmen.carbofootprint.global.enums.statuscode.ErrorStatus;
import com.tantanmen.carbofootprint.global.exception.GeneralException;

/**
 * 음식 사진 인식 실패 예외 (알 수 없는 사진, 여러 음식이 같이 있는 경우)
 */
public class ClassificationFailException extends GeneralException {
	public ClassificationFailException() {
		super(ErrorStatus._CLASSIFICATION_FAIL);
	}
}
