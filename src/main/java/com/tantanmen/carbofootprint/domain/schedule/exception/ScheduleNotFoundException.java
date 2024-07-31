package com.tantanmen.carbofootprint.domain.schedule.exception;

import com.tantanmen.carbofootprint.global.enums.statuscode.BaseCode;
import com.tantanmen.carbofootprint.global.enums.statuscode.ErrorStatus;
import com.tantanmen.carbofootprint.global.exception.GeneralException;

/**
 * 존재하지 않는 일정 예외
 */
public class ScheduleNotFoundException extends GeneralException {
	public ScheduleNotFoundException() {
		super(ErrorStatus._SCHEDULE_NOT_FOUND);
	}
}
