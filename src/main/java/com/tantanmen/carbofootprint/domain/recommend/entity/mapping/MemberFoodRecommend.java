package com.tantanmen.carbofootprint.domain.recommend.entity.mapping;

import java.util.List;

import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.global.entity.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자 음식 추천 내역 Entity
 */
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member_food_recommend")
public class MemberFoodRecommend extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "recommend_allergen", nullable = false)
	private String recommendAllergen;

	@Column(name = "recommend_preference", nullable = false)
	private String recommendPreference;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@OneToMany(mappedBy = "memberFoodRecommend", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FoodRecommendResult> foodRecommendResultList;

	//연관 관계 편의 메서드
	public void changeMember(Member member){
		this.member = member;
	}

	public void addFoodRecommendResult(FoodRecommendResult foodRecommendResult){
		foodRecommendResult.changeMemberFoodRecommend(this);
		foodRecommendResultList.add(foodRecommendResult);
	}
}
