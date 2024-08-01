package com.tantanmen.carbofootprint.domain.classification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tantanmen.carbofootprint.domain.classification.entity.ClassificationResult;

/**
 * 사진 인식 결과 저장 repository
 */

@Repository
public interface ClassificationRepository extends JpaRepository<ClassificationResult, Long> {
}
