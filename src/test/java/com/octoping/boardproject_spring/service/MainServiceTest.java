package com.octoping.boardproject_spring.service;

import com.octoping.boardproject_spring.domain.Member;
import com.octoping.boardproject_spring.repository.MemberRepository;
import com.octoping.boardproject_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainServiceTest {
    MainService mainService = new MainService();

    @AfterEach
    public void clearRepository() {
        mainService.memberRepository.clearStore();
    }

    @Test
    public void loginTest() {
        mainService.memberRepository.save(new Member("myc", "123"));
        boolean resultTrue = mainService.login("myc", "123");
        boolean resultFalse1 = mainService.login("myc", "456");
        boolean resultFalse2 = mainService.login("noData", "123");

        Assertions.assertTrue(resultTrue);
        Assertions.assertFalse(resultFalse1);
        Assertions.assertFalse(resultFalse2);
    }

    @Test
    public void joinNewMemberTest() {
        boolean resultTrue = mainService.joinNewMember("myc", "123");
        boolean resultFalse = mainService.joinNewMember("myc", "456");

        Assertions.assertTrue(resultTrue);
        Assertions.assertFalse(resultFalse);
    }


}
