package com.tantanmen.carbofootprint.domain.user.repository;

import com.tantanmen.carbofootprint.domain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByLoginId(String loginId);
    boolean existsByPassword(String password);
    Optional<Member> findByLoginId(String loginId);
}
