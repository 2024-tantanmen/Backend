package com.tantanmen.carbofootprint.domain.schedule.entity;

import java.util.List;

import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.schedule.entity.meal.FirstMeal;
import com.tantanmen.carbofootprint.domain.schedule.entity.meal.OtherMeal;
import com.tantanmen.carbofootprint.domain.schedule.entity.meal.SecondMeal;
import com.tantanmen.carbofootprint.domain.schedule.entity.meal.ThirdMeal;
import com.tantanmen.carbofootprint.global.entity.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 탄수 발자국 일정 Entity
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schedule")
public class Schedule extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "total_kcal")
	private Long totalKcal;

	@Column(name = "exercise_duration")
	private Long exerciseDuration;

	@Column(name = "step_count")
	private Long stepCount;

	@Column(name = "month")
	private Long month;

	@Column(name = "day")
	private Long day;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FirstMeal> firstMealList;

	@OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SecondMeal> secondMealList;

	@OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ThirdMeal> thirdMealList;

	@OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OtherMeal> otherMealList;


	// 연관 관계 편의 메서드
	public void addFirstMeal(FirstMeal firstMeal){
		firstMeal.changeSchedule(this);
		firstMealList.add(firstMeal);
	}

	public void addSecondMeal(SecondMeal secondMeal){
		secondMeal.changeSchedule(this);
		secondMealList.add(secondMeal);
	}

	public void addThirdMeal(ThirdMeal thirdMeal){
		thirdMeal.changeSchedule(this);
		thirdMealList.add(thirdMeal);
	}

	public void addOtherMeal(OtherMeal otherMeal){
		otherMeal.changeSchedule(this);
		otherMealList.add(otherMeal);
	}

	public void changeMember(Member member){
		this.member = member;
	}
}
