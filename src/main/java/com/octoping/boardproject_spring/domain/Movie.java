package com.octoping.boardproject_spring.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {
    private Long movieId;
    private String name;
    private String directorName;
    private String filePath;

    public Movie(String name, String directorName) {
        this.name = name;
        this.directorName = directorName;
    }
}
