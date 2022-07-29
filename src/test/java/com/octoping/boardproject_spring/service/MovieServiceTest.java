package com.octoping.boardproject_spring.service;

import com.octoping.boardproject_spring.domain.Movie;
import com.octoping.boardproject_spring.repository.MemoryMovieRepository;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MovieServiceTest {
    MovieService movieService = new MovieService(new MemoryMovieRepository());

    @AfterEach
    public void clearRepository() {
        movieService.movieRepository.clearStore();
    }

    @Test
    public void getMovieTest() {
        movieService.movieRepository.save(new Movie("화양연화", "왕가위"));

        Optional<Movie> movieOptional = movieService.getMovie(1);
        Assertions.assertTrue(movieOptional.filter(m -> m.getName().equals("화양연화")).isPresent());
        
        Optional<Movie> movieOptional2 = movieService.getMovie(2);
        Assertions.assertFalse(movieOptional2.isPresent());
    }

    @Test
    public void setMovieFilePathTest() {
        movieService.movieRepository.save(new Movie("기생충", "봉준호"));
        movieService.movieRepository.save(new Movie("인터스텔라", "크리스토퍼 놀란"));
        movieService.movieRepository.save(new Movie("헤어질 결심", "박찬욱"));

        movieService.setMovieFilePath(1, "C:/parasite.mp4");
        movieService.setMovieFilePath(2, "C:/src/main/index.html");

        String filePath1 = movieService.movieRepository.findBymovieId(1).map(Movie::getFilePath).orElse("Fail");
        String filePath2 = movieService.movieRepository.findBymovieId(2).map(Movie::getFilePath).orElse("Fail");
        String filePath3 = movieService.movieRepository.findBymovieId(3).map(Movie::getFilePath).orElse("Fail");
        String filePath4 = movieService.movieRepository.findBymovieId(999).map(Movie::getFilePath).orElse("Fail");
        boolean resultTrue1 = filePath1.equals("C:/parasite.mp4");
        boolean resultFalse = filePath2.equals("C:/handmaiden.mp4");
        boolean resultTrue2 = filePath3.equals("Fail");
        boolean resultTrue3 = filePath4.equals("Fail");


        Assertions.assertTrue(resultTrue1);
        Assertions.assertFalse(resultFalse);
        Assertions.assertTrue(resultTrue2);
        Assertions.assertTrue(resultTrue3);
    }
}
