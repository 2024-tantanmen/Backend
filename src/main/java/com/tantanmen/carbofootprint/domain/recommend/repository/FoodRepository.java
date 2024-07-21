package com.tantanmen.carbofootprint.domain.recommend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tantanmen.carbofootprint.domain.recommend.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
	@Query("SELECT DISTINCT f FROM Food f " +
		"LEFT JOIN f.foodAllergenList fa " +
		"LEFT JOIN fa.allergen a " +
		"JOIN f.foodPreferenceList fp " +
		"JOIN fp.preference p " +
		"WHERE (a IS NULL OR a.allergenName NOT IN :allergens) " +
		"AND p.preferenceName IN :preferences")
	List<Food> findRecommendedFoods(@Param("allergens") List<String> allergens,
		@Param("preferences") List<String> preferences);
}
