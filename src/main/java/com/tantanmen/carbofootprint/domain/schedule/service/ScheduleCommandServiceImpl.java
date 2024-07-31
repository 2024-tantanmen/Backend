package com.tantanmen.carbofootprint.domain.schedule.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.schedule.convertor.ScheduleConvertor;
import com.tantanmen.carbofootprint.domain.schedule.entity.Schedule;
import com.tantanmen.carbofootprint.domain.schedule.repository.ScheduleRepository;
import com.tantanmen.carbofootprint.domain.schedule.web.dto.ScheduleRequestDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleCommandServiceImpl implements ScheduleCommandService{
	private final ScheduleRepository scheduleRepository;

	/**
	 * 일정 데이터 저장
	 */
	@Override
	public Schedule addSchedule(ScheduleRequestDto.AddScheduleRequestDto request, Member member){
		// Schedule 객체 변환 후 저장
		Schedule schedule = ScheduleConvertor.toSchedule(request);

		member.addSchedule(schedule);

		scheduleRepository.save(schedule);

		return schedule;
	}
}
