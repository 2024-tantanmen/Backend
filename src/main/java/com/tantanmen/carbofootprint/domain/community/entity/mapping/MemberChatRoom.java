package com.tantanmen.carbofootprint.domain.community.entity.mapping;

import com.tantanmen.carbofootprint.domain.Member.entity.Member;
import com.tantanmen.carbofootprint.domain.community.entity.ChatRoom;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자 채팅방 입장 내역 저장 Entity
 */
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member_chat_room")
public class MemberChatRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "enter_chat_id", nullable = false)
	private Long enterChatId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chat_room_id")
	private ChatRoom chatRoom;

	// 연관 관계 편의 메서드
	public void changeMember(Member member) {
		this.member = member;
	}

	public void changeChatRoom(ChatRoom chatRoom) {
		this.chatRoom = chatRoom;
	}

}
