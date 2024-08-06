package com.tantanmen.carbofootprint.domain.recommend.entity;

import java.util.List;

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
@Table(name = "preferences")
public class Preference {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "preference_name", nullable = false)
	private String preferenceName;

	@OneToMany(mappedBy = "preference", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FoodPreference> foodPreferenceList;

	// 연관 관계 편의 메서드
	public void addFoodPreference(FoodPreference foodPreference){
		foodPreference.changePreference(this);
		foodPreferenceList.add(foodPreference);
	}
}
