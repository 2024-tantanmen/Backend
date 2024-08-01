package com.tantanmen.carbofootprint.domain.recommend.entity;

import java.util.List;

import com.tantanmen.carbofootprint.domain.recommend.entity.mapping.FoodAllergen;
import com.tantanmen.carbofootprint.domain.recommend.entity.mapping.FoodPreference;
import com.tantanmen.carbofootprint.domain.recommend.entity.mapping.FoodRecommendResult;

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

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food_recommend")
public class FoodRecommend {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "food_name", nullable = false)
	private String foodName;

	@Column(name = "food_image_url")
	private String imageUrl;

	@Column(name = "calorie", nullable = false)
	private Integer calorie;

	@Column(name = "carbohydrate", nullable = false)
	private Integer carbohydrate;

	@Column(name = "saccharide", nullable = false)
	private Integer saccharide;

	@OneToMany(mappedBy = "foodRecommend", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FoodAllergen> foodAllergenList;

	@OneToMany(mappedBy = "foodRecommend", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FoodPreference> foodPreferenceList;

	@OneToMany(mappedBy = "foodRecommend", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FoodRecommendResult> foodRecommendResultList;

	// 연관관계 편의 메서드
	public void addFoodAllergen(FoodAllergen foodAllergen) {
		foodAllergen.changeFoodRecommend(this);
		foodAllergenList.add(foodAllergen);
	}

	public void addFoodPreference(FoodPreference foodPreference) {
		foodPreference.changeFoodRecommend(this);
		foodPreferenceList.add(foodPreference);
	}

	public void addFoodRecommendResult(FoodRecommendResult foodRecommendResult){
		foodRecommendResult.changeFoodRecommend(this);
		foodRecommendResultList.add(foodRecommendResult);
	}
}
