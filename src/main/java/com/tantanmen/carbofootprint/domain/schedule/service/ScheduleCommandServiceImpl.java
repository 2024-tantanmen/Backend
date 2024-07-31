package com.tantanmen.carbofootprint.domain.schedule.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.schedule.convertor.ScheduleConvertor;
import com.tantanmen.carbofootprint.domain.schedule.entity.Schedule;
import com.tantanmen.carbofootprint.domain.schedule.exception.ScheduleNotFoundException;
import com.tantanmen.carbofootprint.domain.schedule.repository.ScheduleRepository;
import com.tantanmen.carbofootprint.domain.schedule.web.dto.ScheduleRequestDto;
import com.tantanmen.carbofootprint.global.enums.statuscode.ErrorStatus;
import com.tantanmen.carbofootprint.global.exception.GeneralException;

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

	/**
	 * 일정 데이터 수정
	 */
	@Override
	public Schedule updateSchedule(ScheduleRequestDto.AddScheduleRequestDto request, Long scheduleId, Member member){
		// 일정 검색, 없는 경우 예외
		Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new ScheduleNotFoundException());

		// 사용자가 작성한 일정이 아닌 경우 예외 처리
		if(schedule.getMember().getId() != member.getId()){
			throw new GeneralException(ErrorStatus._SCHEDULE_FORBIDDEN);
		}

		schedule.updateData(request);

		return scheduleRepository.save(schedule);
	}
}
