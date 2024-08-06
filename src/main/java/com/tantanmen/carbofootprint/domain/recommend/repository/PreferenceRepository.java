package com.tantanmen.carbofootprint.domain.recommend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tantanmen.carbofootprint.domain.recommend.entity.Preference;

@Repository
public interface PreferenceRepository extends JpaRepository<Preference, Long> {

}
