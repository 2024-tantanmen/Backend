package com.tantanmen.carbofootprint.domain.recommend.entity;

import java.util.List;

import com.tantanmen.carbofootprint.domain.recommend.entity.mapping.FoodAllergen;

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
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "allergens")
public class Allergen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "allergen_name", nullable = false)
	private String allergenName;

	@OneToMany(mappedBy = "allergen", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FoodAllergen> foodAllergenList;

	// 연관 관계 편의 메서드
	public void addFoodAllergen(FoodAllergen foodAllergen){
		foodAllergen.changeAllergen(this);
		foodAllergenList.add(foodAllergen);
	}
}
