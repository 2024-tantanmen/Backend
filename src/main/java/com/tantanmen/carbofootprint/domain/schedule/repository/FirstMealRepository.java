package com.tantanmen.carbofootprint.domain.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tantanmen.carbofootprint.domain.schedule.entity.meal.FirstMeal;

/**
 * 첫 끼 repository
 */
@Repository
public interface FirstMealRepository extends JpaRepository<FirstMeal, Long> {
}
