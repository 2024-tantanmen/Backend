package com.tantanmen.carbofootprint.domain.member.repository;

import com.tantanmen.carbofootprint.domain.member.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
