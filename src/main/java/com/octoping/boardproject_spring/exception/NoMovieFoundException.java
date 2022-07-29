package com.octoping.boardproject_spring.exception;

public class NoMovieFoundException extends Exception {
    public NoMovieFoundException() {

    }

    public NoMovieFoundException(String movieId) {
        super("No movie found with id: " + movieId);
    }
}