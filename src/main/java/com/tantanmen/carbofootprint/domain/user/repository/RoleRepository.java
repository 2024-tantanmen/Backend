package com.tantanmen.carbofootprint.domain.user.repository;

import com.tantanmen.carbofootprint.domain.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
