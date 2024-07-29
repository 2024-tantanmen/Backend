package com.tantanmen.carbofootprint.domain.community.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tantanmen.carbofootprint.domain.community.entity.ChatRoom;

/**
 * 채팅 방 Repository
 */
@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
	Optional<ChatRoom> findById(Long chatRoomId);
}
