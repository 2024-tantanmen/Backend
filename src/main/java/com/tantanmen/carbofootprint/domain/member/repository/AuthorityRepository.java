package com.tantanmen.carbofootprint.domain.member.repository;

import com.tantanmen.carbofootprint.domain.member.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
