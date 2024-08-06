package com.tantanmen.carbofootprint.domain.classification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tantanmen.carbofootprint.domain.classification.entity.ClassificationResult;

/**
 * 사진 인식 결과 저장 repository
 */

@Repository
public interface ClassificationResultRepository extends JpaRepository<ClassificationResult, Long> {
	List<ClassificationResult> findByMemberId(Long memberId);
}
