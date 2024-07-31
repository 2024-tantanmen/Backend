package com.tantanmen.carbofootprint.domain.member.entity;

import java.util.List;

import com.tantanmen.carbofootprint.domain.community.entity.mapping.ChatMessage;
import com.tantanmen.carbofootprint.domain.community.entity.mapping.MemberChatRoom;
import com.tantanmen.carbofootprint.domain.schedule.entity.Schedule;

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
 * 사용자 Entity
 */
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "login_id", nullable = false)
	private String loginId;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Authority> authorityList;

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MemberChatRoom> memberChatRoomList;

	@OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ChatMessage> chatMessageList;

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Schedule> scheduleList;

	// 연관 관계 편의 메서드
	public void addAuthority(Authority authority) {
		authority.changeMember(this);
		this.authorityList.add(authority);
	}

	public void addMemberChatRoom(MemberChatRoom memberChatRoom) {
		memberChatRoom.changeMember(this);
		memberChatRoomList.add(memberChatRoom);
	}

	public void addChatMessage(ChatMessage chatMessage) {
		chatMessage.changeSender(this);
		chatMessageList.add(chatMessage);
	}

	public void addSchedule(Schedule schedule){
		schedule.changeMember(this);
		scheduleList.add(schedule);
	}
}
