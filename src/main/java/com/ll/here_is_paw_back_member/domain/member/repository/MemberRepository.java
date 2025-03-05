package com.ll.here_is_paw_back_member.domain.member.repository;


import com.ll.here_is_paw_back_member.domain.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
    Optional<Member> findByApiKey(String apiKey);
}
