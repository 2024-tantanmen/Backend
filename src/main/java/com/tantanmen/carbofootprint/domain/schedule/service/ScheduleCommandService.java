package com.tantanmen.carbofootprint.domain.schedule.service;

import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.schedule.entity.Schedule;
import com.tantanmen.carbofootprint.domain.schedule.web.dto.ScheduleRequestDto;

public interface ScheduleCommandService {
	Schedule addSchedule(ScheduleRequestDto.AddScheduleRequestDto request, Member member);
	Schedule updateSchedule(ScheduleRequestDto.AddScheduleRequestDto request, Long scheduleId, Member member);
	Long deleteSchedule(Long scheduleId, Member member);
}

