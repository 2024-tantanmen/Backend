package com.tantanmen.carbofootprint.global.aws.exception;

import com.tantanmen.carbofootprint.global.enums.statuscode.BaseCode;
import com.tantanmen.carbofootprint.global.exception.GeneralException;

public class S3FileSaveException extends GeneralException {
	public S3FileSaveException(BaseCode errorStatus) {
		super(errorStatus);
	}
}
