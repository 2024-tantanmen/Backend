package com.tantanmen.carbofootprint.domain.recommend.entity.mapping;

import com.tantanmen.carbofootprint.domain.recommend.entity.FoodRecommend;
import com.tantanmen.carbofootprint.domain.recommend.entity.Preference;

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

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food_preferences")
public class FoodPreference {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "food_id")
	private FoodRecommend foodRecommend;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "preference_id")
	private Preference preference;

	// 연관 관계 편의 메서드
	public void changeFoodRecommend(FoodRecommend foodRecommend){
		this.foodRecommend = foodRecommend;
	}

	public void changePreference(Preference preference){
		this.preference = preference;
	}

}
