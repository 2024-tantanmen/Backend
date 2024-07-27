package com.tantanmen.carbofootprint.domain.user.repository;

import com.tantanmen.carbofootprint.domain.user.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
