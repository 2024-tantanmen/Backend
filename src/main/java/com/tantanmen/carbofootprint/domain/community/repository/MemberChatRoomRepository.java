package com.tantanmen.carbofootprint.domain.community.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tantanmen.carbofootprint.domain.community.entity.mapping.MemberChatRoom;

@Repository
public interface MemberChatRoomRepository extends JpaRepository<MemberChatRoom, Long> {
	Optional<MemberChatRoom> findByMemberIdAndChatRoomId(Long memberId, Long chatRoomId);
	Optional<MemberChatRoom> findByMemberLoginIdAndChatRoomId(String loginId, Long chatRoomId);
}
