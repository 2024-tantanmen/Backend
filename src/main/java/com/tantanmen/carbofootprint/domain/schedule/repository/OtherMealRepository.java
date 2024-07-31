package com.tantanmen.carbofootprint.domain.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tantanmen.carbofootprint.domain.schedule.entity.meal.OtherMeal;

/**
 * 더먹 끼 repository
 */
@Repository
public interface OtherMealRepository extends JpaRepository<OtherMeal, Long> {
}
