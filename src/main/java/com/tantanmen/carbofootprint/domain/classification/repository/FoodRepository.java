package com.tantanmen.carbofootprint.domain.classification.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tantanmen.carbofootprint.domain.classification.entity.Food;

/**
 * 음식 데이터 repository
 */
@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
	Optional<Food> findByCode(String code);
}
