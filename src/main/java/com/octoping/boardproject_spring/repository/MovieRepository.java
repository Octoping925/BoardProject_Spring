package com.octoping.boardproject_spring.repository;

import com.octoping.boardproject_spring.domain.Movie;
import java.util.List;
import java.util.Optional;

public interface MovieRepository {
    Movie save(Movie movie);
    Optional<Movie> findBymovieId(long movieId);
    Optional<Movie> findByName(String name);
    List<Movie> findAll();
    void clearStore();
}
