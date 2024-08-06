package com.tantanmen.carbofootprint.domain.community.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tantanmen.carbofootprint.domain.community.entity.ChatRoom;

/**
 * 채팅 방 Repository
 */
@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
	Optional<ChatRoom> findById(Long chatRoomId);

	@Query(value = "SELECT cr.* FROM chat_room cr " +
		"WHERE cr.id NOT IN (SELECT mc.chat_room_id FROM member_chat_room mc WHERE mc.member_id = :memberId)",
		nativeQuery = true)
	List<ChatRoom> findAllByMemberNotExist(@Param("memberId") Long memberId);
}
