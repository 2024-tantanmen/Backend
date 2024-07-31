package com.tantanmen.carbofootprint.domain.schedule.service;

import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.schedule.entity.Schedule;
import com.tantanmen.carbofootprint.domain.schedule.web.dto.ScheduleRequestDto;

public interface ScheduleCommandService {
	Schedule addSchedule(ScheduleRequestDto.AddScheduleRequestDto request, Member member);
}
