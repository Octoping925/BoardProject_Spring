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
        initTmpData();
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
        Optional<Movie> movie = movieRepository.findBymovieId(movieId);
        movie.ifPresent(m -> m.setFilePath(filePath));
    }

    // 수동으로 영화 데이터 추가
    public void initTmpData() {
        addMovie("그 여름 가장 조용한 바다", "기타노 다케시");
        addMovie("릴리 슈슈의 모든 것", "이와이 슌지");
        addMovie("7인의 사무라이", "구로사와 아키라");
        setMovieFilePath(1, "C:/ap.mp4");
        setMovieFilePath(2, "C:/movie/Heat1995.mp4");
        setMovieFilePath(3, "C:/ap2.mp4");
    }


}
