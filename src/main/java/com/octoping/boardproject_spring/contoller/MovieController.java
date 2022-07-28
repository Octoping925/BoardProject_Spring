package com.octoping.boardproject_spring.contoller;

import com.octoping.boardproject_spring.domain.Movie;
import com.octoping.boardproject_spring.service.MovieService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    }

    @GetMapping("/movie/movieList")
    public String movieList(Model model) {
        List<Movie> movieList = movieService.getMovieList();
        model.addAttribute("movieList", movieList);
        return "/movie/movieList";
    }

    @GetMapping("/movie/watch")
    public ModelAndView watch(@RequestParam String movieId){
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
}
