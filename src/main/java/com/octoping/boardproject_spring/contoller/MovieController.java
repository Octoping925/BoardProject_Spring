package com.octoping.boardproject_spring.contoller;

import com.octoping.boardproject_spring.domain.Movie;
import com.octoping.boardproject_spring.service.MovieService;
import com.octoping.boardproject_spring.exception.NoMovieFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/movie/watch/{movieId}")
    public ModelAndView watchMovie(@PathVariable String movieId) {
        ModelAndView mav = new ModelAndView();
        Optional<Movie> movie = movieService.getMovie(Long.parseLong(movieId));
        
        try {
            movie.orElseThrow(() -> new NoMovieFoundException("No Movie Found with id: " + movieId));
            mav.setViewName("/movie/watch");
            mav.addObject("movie", movie.get());
        }
        catch(NoMovieFoundException e) {
            e.printStackTrace();
            mav.setViewName("redirect:/movie/movieList");
        }

        return mav;
    }
}
