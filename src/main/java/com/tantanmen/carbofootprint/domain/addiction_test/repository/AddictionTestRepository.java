package com.tantanmen.carbofootprint.domain.addiction_test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tantanmen.carbofootprint.domain.addiction_test.entity.AddictionTest;

/**
 * 탄수 중독 테스트 데이터 repository
 */

@Repository
public interface AddictionTestRepository extends JpaRepository<AddictionTest, Long> {
	List<AddictionTest> findByMemberId(Long memberId);
}
