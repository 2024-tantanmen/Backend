package com.tantanmen.carbofootprint.domain.member.repository;

import com.tantanmen.carbofootprint.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByLoginId(String loginId);
    Optional<Member> findByLoginId(String loginId);
	Optional<Member> findById(Long memberId);
}
