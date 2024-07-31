package com.tantanmen.carbofootprint.domain.schedule.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.schedule.entity.Schedule;
import com.tantanmen.carbofootprint.domain.schedule.repository.ScheduleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleQueryServiceImpl implements ScheduleQueryService{
	private final ScheduleRepository scheduleRepository;

	/**
	 * 사용자가 설정한 일정 모두 조회
	 */
	@Override
	public List<Schedule> getAllSchedules(Member member){
		return scheduleRepository.findByMemberId(member.getId());
	}
}
