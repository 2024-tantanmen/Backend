package com.tantanmen.carbofootprint.domain.recommend.entity;

import java.util.List;

import com.tantanmen.carbofootprint.domain.recommend.entity.mapping.FoodAllergen;
import com.tantanmen.carbofootprint.domain.recommend.entity.mapping.FoodPreference;

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
@Table(name = "foods")
public class Food {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "food_name", nullable = false)
	private String foodName;

	@Column(name = "food_image_url")
	private String imageUrl;

	@OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FoodAllergen> foodAllergenList;

	@OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FoodPreference> foodPreferenceList;

	// 연관관계 편의 메서드
	public void addFoodAllergen(FoodAllergen foodAllergen) {
		foodAllergen.changeFood(this);
		foodAllergenList.add(foodAllergen);
	}

	public void addFoodPreference(FoodPreference foodPreference) {
		foodPreference.changeFood(this);
		foodPreferenceList.add(foodPreference);
	}
}
