package com.octoping.boardproject_spring.repository;

import com.octoping.boardproject_spring.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByUid(Long uid);
    Optional<Member> findById(String id);
    List<Member> findAll();
    boolean isUserInfoValid(String id, String password);
}
