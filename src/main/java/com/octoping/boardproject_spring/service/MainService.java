package com.octoping.boardproject_spring.service;

import com.octoping.boardproject_spring.domain.Member;
import com.octoping.boardproject_spring.repository.MemberRepository;
import com.octoping.boardproject_spring.repository.MemoryMemberRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MainService {
    MemberRepository memberRepository = new MemoryMemberRepository();

    public boolean login(String id, String password) {
        Optional<Member> member = memberRepository.findById(id);
        if(member.isEmpty()) { // 아이디가 없을 경우
            return false;
        }

        if(member.filter(m -> password.equals(m.getPassword())).isEmpty()) { // 비밀번호가 틀렸을 경우
            return false;
        }

        return true;
    }

    public boolean joinNewMember(String id, String password) {
        if(memberRepository.findById(id).isPresent()) {
            return false;
        }

        memberRepository.save(new Member(id, password));
        return true;
    }

}
