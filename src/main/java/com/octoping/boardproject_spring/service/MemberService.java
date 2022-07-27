package com.octoping.boardproject_spring.service;

import com.octoping.boardproject_spring.domain.Member;
import com.octoping.boardproject_spring.repository.MemberRepository;
import com.octoping.boardproject_spring.repository.MemoryMemberRepository;
import com.octoping.boardproject_spring.repository.MemoryMovieRepository;
import com.octoping.boardproject_spring.repository.MovieRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    MemberRepository memberRepository = new MemoryMemberRepository();

    public boolean login(String id, String password) {
        Optional<Member> member = memberRepository.findById(id);
/*      TODO: 아이디가 존재하지 않을 때, 비밀번호만 다를 때 분기 쳐서 다른 메시지 출력하도록 수정
        if(member.isEmpty()) { // 아이디가 없을 경우
            return false;
        }

        if(member.filter(m -> password.equals(m.getPassword())).isEmpty()) { // 비밀번호가 틀렸을 경우
            return false;
        } */
        return member.filter(m -> password.equals(m.getPassword())).isPresent();
    }

    public boolean joinNewMember(String id, String password) {
        if(memberRepository.findById(id).isPresent()) {
            return false;
        }

        memberRepository.save(new Member(id, password));
        return true;
    }
}
