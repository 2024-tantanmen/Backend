package com.tantanmen.carbofootprint.domain.community.entity;

import java.util.List;

import com.tantanmen.carbofootprint.domain.community.entity.mapping.ChatMessage;
import com.tantanmen.carbofootprint.domain.community.entity.mapping.MemberChatRoom;
import com.tantanmen.carbofootprint.global.entity.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 채팅방 Entity
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat_room")
public class ChatRoom extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "chat_room_name", nullable = false)
	private String name;

	@Column(name = "max_capacity", nullable = false)
	private Integer maxCapacity;

	@Column(name = "current_capacity", nullable = false)
	private Integer currentCapacity;

	@OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ChatMessage> chatMessageList;

	@OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MemberChatRoom> memberChatRoomList;

	// 연관 관계 편의 메서드
	public void addChatMessage(ChatMessage chatMessage) {
		chatMessage.changeChatRoom(this);
		chatMessageList.add(chatMessage);
	}

	public void addMemberChatRoom(MemberChatRoom memberChatRoom) {
		memberChatRoom.changeChatRoom(this);
		memberChatRoomList.add(memberChatRoom);
	}
}
