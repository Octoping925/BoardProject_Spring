package com.octoping.boardproject_spring.service;

import com.octoping.boardproject_spring.domain.Movie;
import com.octoping.boardproject_spring.repository.MemoryMovieRepository;
import com.octoping.boardproject_spring.repository.MovieRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    MovieRepository movieRepository;

    @Autowired
    public MovieService(MemoryMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovieList() {
        return movieRepository.findAll();
    }

    public boolean addMovie(String movieName, String directorName) {
        movieRepository.save(new Movie(movieName, directorName));
        return true;
    }

    public Optional<Movie> getMovie(long movieId) {
        return movieRepository.findBymovieId(movieId);
    }

    public void setMovieFilePath(long movieId, String filePath) {
        movieRepository.findBymovieId(movieId).get().setFilePath(filePath);
    }


}
