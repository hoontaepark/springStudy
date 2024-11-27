package com.example.springStudy.member.infrastructure;

import com.example.springStudy.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByName(String name);
    boolean existsByEmail(String email);

}
