package com.tantanmen.carbofootprint.domain.schedule.service;

import java.util.List;

import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.schedule.entity.Schedule;

public interface ScheduleQueryService {
	List<Schedule> getAllSchedules(Member member);
}
