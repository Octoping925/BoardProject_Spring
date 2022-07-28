package com.octoping.boardproject_spring.repository;

import com.octoping.boardproject_spring.domain.Movie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryMovieRepository implements MovieRepository{
    private static Map<Long, Movie> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Movie save(Movie movie) {
        movie.setMovieId(++sequence);
        store.put(movie.getMovieId(), movie);
        return movie;
    }

    @Override
    public Optional<Movie> findBymovieId(long movieId) {
        return Optional.ofNullable(store.get(Long.valueOf(movieId)));
    }

    @Override
    public Optional<Movie> findByName(String name) {
        return store.values().stream()
            .filter(movie -> movie.getName().equals(name))
            .findAny();
    }

    @Override
    public List<Movie> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
