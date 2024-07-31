package com.tantanmen.carbofootprint.domain.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tantanmen.carbofootprint.domain.schedule.entity.Schedule;

/**
 * 일정 repository
 */

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
