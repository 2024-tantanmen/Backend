package com.tantanmen.carbofootprint.domain.recommend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tantanmen.carbofootprint.domain.recommend.entity.mapping.MemberFoodRecommend;

/**
 * 음식 추천 결과 데이터 repository
 */
@Repository
public interface MemberFoodRecommendRepository extends JpaRepository<MemberFoodRecommend, Long> {
	List<MemberFoodRecommend> findByMemberId(Long memberId);
}
