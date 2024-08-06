package com.tantanmen.carbofootprint.global.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.tantanmen.carbofootprint.global.enums.statuscode.BaseCode;
import com.tantanmen.carbofootprint.global.enums.statuscode.SuccessStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {
	@Schema(description = "성공 여부", example = "true")
	private final Boolean isSuccess;
	@Schema(description = "응답 코드", example = "USER4001")
	private final String code;
	@Schema(description = "응답 메세지", example = "성공입니다.")
	private final String message;
	@Schema(description = "응답 데이터")
	private T result;

	// 성공한 경우 응답 생성

	public static <T> ApiResponse<T> onSuccess(T result) {
		return new ApiResponse<>(true, SuccessStatus._OK.getCode(), SuccessStatus._OK.getMessage(), result);
	}

	public static <T> ApiResponse<T> of(boolean isSuccess, BaseCode code, T result) {
		return new ApiResponse<>(isSuccess, code.getCode(), code.getMessage(), result);
	}

	// 실패한 경우 응답 생성
	public static <T> ApiResponse<T> onFailure(String code, String message, T data) {
		return new ApiResponse<>(false, code, message, data);
	}
}
