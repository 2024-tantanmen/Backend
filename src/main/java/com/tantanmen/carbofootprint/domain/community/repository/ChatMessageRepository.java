package com.tantanmen.carbofootprint.domain.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tantanmen.carbofootprint.domain.community.entity.mapping.ChatMessage;

/**
 * 채팅 메시지 Repository
 */
@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

	/**
	 * 사용자 입장 이후의 채팅 목록 조회
	 */

	List<ChatMessage> findAllByChatRoomIdAndIdGreaterThanEqualOrderByIdAsc(Long chatRoomId, Long enterChatId);

	/**
	 * 특정 채팅방의 채팅 개수 조회
	 */
	@Query("SELECT COUNT(c) FROM ChatMessage c WHERE c.chatRoom.id = :chatRoomId")
	Long findChatMessageCountByChatRoomId(@Param("chatRoomId") Long chatRoomId);

}
