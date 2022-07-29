package com.octoping.boardproject_spring.contoller;

import com.octoping.boardproject_spring.domain.Movie;
import com.octoping.boardproject_spring.service.MovieService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

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
        movieService.setMovieFilePath(2, "C:/movie/Heat1995.mp4");
    }

    @GetMapping("/movie/movieList")
    public String movieList(Model model) {
        List<Movie> movieList = movieService.getMovieList();
        model.addAttribute("movieList", movieList);
        return "/movie/movieList";
    }

    @GetMapping("/movie/watch/{movieId}")
    public ModelAndView watch(@PathVariable("movieId") String movieId){
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

    @GetMapping("/movie/test/{movieId}") // 스트리밍 테스트용
    public ResponseEntity<StreamingResponseBody> movieTest(@PathVariable("movieId") String movieId, Model model) {
        Movie movie =  movieService.getMovie(Long.parseLong(movieId)).orElse(new Movie("test", "testDirector"));
        File file = new File(movie.getFilePath());
        if(!file.isFile()) {
            return ResponseEntity.notFound().build();
        }

        StreamingResponseBody streamingResponseBody = outputStream -> FileCopyUtils.copy(new FileInputStream(file), outputStream);

//        StreamingResponseBody streamingResponseBody = outputStream -> {
//            try {
//                final InputStream inputStream = new FileInputStream(file);
//                byte[] bytes = new byte[1024];
//                int length;
//
//                while((length = inputStream.read(bytes)) >= 0) {
//                    outputStream.write(bytes, 0, length);
//                }
//
//                inputStream.close();
//                outputStream.close();
//            }
//            catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("Exception while reading and streaming Data");
//                System.out.println(movie.getName());
//            }
//        };

        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "video/mp4");
        responseHeaders.add("Content-Length", Long.toString(file.length()));

        return ResponseEntity.ok().headers(responseHeaders).body(streamingResponseBody);
    }

    @GetMapping("/movie/getMovieData")
    public String getMovieData(@RequestParam("movieId") String movieId) {

        return "";
    }
}
