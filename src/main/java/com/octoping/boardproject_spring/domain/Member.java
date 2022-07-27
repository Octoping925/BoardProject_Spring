package com.octoping.boardproject_spring.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {
    private Long uid;
    private String id;
    private String password;

    public Member(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
