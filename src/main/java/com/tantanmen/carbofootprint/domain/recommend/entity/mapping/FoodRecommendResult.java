package com.tantanmen.carbofootprint.domain.recommend.entity.mapping;

import com.tantanmen.carbofootprint.domain.recommend.entity.FoodRecommend;

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
 * 음식 추천 결과 내역 음식 Mapping Entity
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food_recommend_result")
public class FoodRecommendResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_food_recommend_id")
	private MemberFoodRecommend memberFoodRecommend;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "food_recommend_id")
	private FoodRecommend foodRecommend;

	// 연관 관계 편의 메서드
	public void changeMemberFoodRecommend(MemberFoodRecommend memberFoodRecommend){
		this.memberFoodRecommend = memberFoodRecommend;
	}

	public void changeFoodRecommend(FoodRecommend foodRecommend) {
		this.foodRecommend = foodRecommend;
	}
}
