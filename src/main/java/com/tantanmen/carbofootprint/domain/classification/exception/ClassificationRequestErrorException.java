package com.tantanmen.carbofootprint.domain.classification.exception;

import com.tantanmen.carbofootprint.global.enums.statuscode.ErrorStatus;
import com.tantanmen.carbofootprint.global.exception.GeneralException;

/**
 * AI 서버 요청 중 에러 발생 예외
 */
public class ClassificationRequestErrorException extends GeneralException {
	public ClassificationRequestErrorException() {
		super(ErrorStatus._CLASSIFICAION_REQUEST_ERROR);
	}
}
