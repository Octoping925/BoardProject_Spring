package com.octoping.boardproject_spring.repository;

import com.octoping.boardproject_spring.domain.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setUid(++sequence);
        store.put(member.getUid(), member);
        return member;
    }

    @Override
    public Optional<Member> findByUid(Long uid) {
        return Optional.ofNullable(store.get(uid));
    }

    @Override
    public Optional<Member> findById(String id) {
        return store.values().stream()
            .filter(member -> member.getId().equals(id))
            .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void clearStore() {
        store.clear();
    }

    @Override
    public boolean isUserInfoValid(String id, String password) {
        Optional<Member> member = this.findById(id);
        return member.filter(m -> password.equals(m.getPassword())).isPresent();
    }


}
