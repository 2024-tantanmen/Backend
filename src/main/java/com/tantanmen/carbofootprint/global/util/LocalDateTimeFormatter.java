package com.tantanmen.carbofootprint.global.util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeFormatter {
	/**
	 * LocalDateTime => MM.dd.x요일 형태로 변환
	 */
	public static String formatLocalDateTime(LocalDateTime dateTime) {

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM.dd");
		String datePart = dateTime.format(dateFormatter);

		DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
		String dayPart = getDayOfWeekInKorean(dayOfWeek);

		return datePart + "." + dayPart + "요일";
	}

	/**
	 * 요일 한글로 변환
	 */
	private static String getDayOfWeekInKorean(DayOfWeek dayOfWeek) {
		switch (dayOfWeek) {
			case MONDAY:
				return "월";
			case TUESDAY:
				return "화";
			case WEDNESDAY:
				return "수";
			case THURSDAY:
				return "목";
			case FRIDAY:
				return "금";
			case SATURDAY:
				return "토";
			case SUNDAY:
				return "일";
			default:
				return "";
		}
	}

}
