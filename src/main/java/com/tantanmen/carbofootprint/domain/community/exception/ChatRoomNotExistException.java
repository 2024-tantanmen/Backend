package com.tantanmen.carbofootprint.domain.community.exception;

import com.tantanmen.carbofootprint.global.enums.statuscode.ErrorStatus;
import com.tantanmen.carbofootprint.global.exception.GeneralException;

/**
 * 채팅방이 존재하지 않는 ID값인 경우 발생하는 예외
 */
public class ChatRoomNotExistException extends GeneralException {
	public ChatRoomNotExistException() {
		super(ErrorStatus._CHAT_ROOM_NOT_EXIST);
	}
}
