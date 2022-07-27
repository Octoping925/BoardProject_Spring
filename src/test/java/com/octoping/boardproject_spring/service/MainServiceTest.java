package com.octoping.boardproject_spring.service;

import com.octoping.boardproject_spring.domain.Member;
import com.octoping.boardproject_spring.repository.MemberRepository;
import com.octoping.boardproject_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainServiceTest {
    MainService mainService = new MainService();

    @Test
    public void joinNewMemberTest() {
        boolean resultTrue = mainService.joinNewMember("myc", "123");
        boolean resultFalse = mainService.joinNewMember("myc", "456");

        Assertions.assertTrue(resultTrue);
        Assertions.assertFalse(resultFalse);
    }


}
