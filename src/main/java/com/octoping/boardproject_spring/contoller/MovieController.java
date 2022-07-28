package com.octoping.boardproject_spring.contoller;

import com.octoping.boardproject_spring.domain.Movie;
import com.octoping.boardproject_spring.service.MovieService;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MovieController {
    MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;

        // 테스트용 데이터
        movieService.addMovie("그 여름 가장 조용한 바다", "기타노 다케시");
        movieService.addMovie("릴리 슈슈의 모든 것", "이와이 슌지");
        movieService.addMovie("7인의 사무라이", "구로사와 아키라");
        movieService.setMovieFilePath(1, "C:/ap.mp4");
        movieService.setMovieFilePath(2, "C:/movie/heat.mp4");
    }

    @GetMapping("/movie/movieList")
    public String movieList(Model model) {
        List<Movie> movieList = movieService.getMovieList();
        model.addAttribute("movieList", movieList);
        return "/movie/movieList";
    }

    @GetMapping("/movie/watch")
    public ModelAndView watch(@RequestParam("movieId") String movieId){
        ModelAndView mav = new ModelAndView();
        Optional<Movie> movie = movieService.getMovie(Long.parseLong(movieId));

        if(movie.isPresent()) {
            mav.setViewName("/movie/watch");
            mav.addObject("movie", movie.get());
        }
        else {
            mav.setViewName("redirect:/movie/movieList");
        }

        return mav;
    }

    @GetMapping("/movie/test") // 스트리밍 테스트용
    public ResponseEntity<Resource> movieTest(Model model) {
        Movie movie =  movieService.getMovie(1).get();
        Resource resource = new FileSystemResource(movie.getFilePath());
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "ap.mp4");
        headers.setContentType(MediaType.parseMediaType("video/mp4"));

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @GetMapping("/movie/getMovieData")
    public String getMovieData(@RequestParam("movieId") String movieId) {

        return "";
    }
}
