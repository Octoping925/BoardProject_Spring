package com.octoping.boardproject_spring.service;

import com.octoping.boardproject_spring.domain.Member;
import com.octoping.boardproject_spring.repository.MemoryMemberRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService = new MemberService(new MemoryMemberRepository());
    
    @AfterEach
    public void clearRepository() {
        memberService.memberRepository.clearStore();
    }

    @Test
    public void loginTest() {
        memberService.memberRepository.save(new Member("myc", "123"));
        boolean resultTrue = memberService.login("myc", "123");
        boolean resultFalse1 = memberService.login("myc", "456");
        boolean resultFalse2 = memberService.login("noData", "123");

        Assertions.assertTrue(resultTrue);
        Assertions.assertFalse(resultFalse1);
        Assertions.assertFalse(resultFalse2);
    }

    @Test
    public void joinNewMemberTest() {
        boolean resultTrue = memberService.joinNewMember("myc", "123");
        boolean resultFalse = memberService.joinNewMember("myc", "456");

        Assertions.assertTrue(resultTrue);
        Assertions.assertFalse(resultFalse);
    }
}
