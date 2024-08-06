package com.tantanmen.carbofootprint.domain.member.exception;

import com.tantanmen.carbofootprint.global.enums.statuscode.ErrorStatus;
import com.tantanmen.carbofootprint.global.exception.GeneralException;

/**
 * 존재하지 않는 사용자 예외
 */
public class MemberNotExistException extends GeneralException {
	public MemberNotExistException() {
		super(ErrorStatus._MEMBER_NOT_EXIST);
	}
}
