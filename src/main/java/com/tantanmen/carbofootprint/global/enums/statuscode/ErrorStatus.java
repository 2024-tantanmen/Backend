package com.tantanmen.carbofootprint.global.enums.statuscode;

import org.springframework.http.HttpStatus;

public enum ErrorStatus implements BaseCode {
	// common
	_INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
	_BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
	_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
	_FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

	// member
	_EXIST_LOGIN_ID(HttpStatus.BAD_REQUEST, "MEMBER4001", "이미 존재하는 아이디입니다."),
	_MEMBER_NOT_EXIST(HttpStatus.NOT_FOUND, "MEMBER4002", "존재하지 않는 사용자입니다."),
	_INVALID_LOGINID_OR_PASSWORD(HttpStatus.BAD_REQUEST, "MEMBER4003", "비밀번호 혹은 아이디가 올바르지 않습니다."),

	// recommend
	_ALLERGEN_EMPTY_ERROR(HttpStatus.BAD_REQUEST, "RECOMMEND4001", "선택된 알레르기가 존재하지 않습니다."),
	_PREFERENCE_EMPTY_ERROR(HttpStatus.BAD_REQUEST, "RECOMMEND4002", "선택된 식습관이 존재하지 않습니다."),

	// classification
	_CLASSIFICAION_REQUEST_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "CLASSIFICATION5001", "음식 사진 AI 요청 중 오류가 발생했습니다."),
	_CLASSIFICATION_FAIL(HttpStatus.BAD_REQUEST, "CLASSIFICATION4001", "음식 사진 인식에 실패했습니다."),

	// schedule
	_SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, "SCHEDULE4001", "존재하지 않는 일정입니다."),
	_SCHEDULE_FORBIDDEN(HttpStatus.FORBIDDEN, "SCHEDULE4002", "일정에 접근 권한이 없는 사용자입니다."),

	// chat
	_CHAT_ROOM_ENTRY_NOT_FOUND(HttpStatus.NOT_FOUND, "CHAT4001", "입장한 내역이 없는 채팅방입니다."),
	_CHAT_ROOM_NOT_EXIST(HttpStatus.NOT_FOUND, "CHAT4002", "존재하지 않는 채팅방입니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;

	ErrorStatus(HttpStatus httpStatus, String code, String message) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	@Override
	public Integer getStatusValue() {
		return httpStatus.value();
	}
}
